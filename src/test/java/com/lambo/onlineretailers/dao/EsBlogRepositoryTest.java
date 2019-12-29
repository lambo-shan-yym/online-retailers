package com.lambo.onlineretailers.dao;

import com.alibaba.fastjson.JSONObject;
import com.lambo.onlineretailers.EsIndex.EsBlog;
import com.lambo.onlineretailers.EsIndex.EsBlogSuggest;
import com.lambo.onlineretailers.config.HighLightResultMapper;
import com.lambo.onlineretailers.dao.es.EsBlogRepository;
import com.lambo.onlineretailers.dao.es.EsBlogSuggestRepository;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.completion.Completion;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.*;

/**
 * @author lambo
 */
@SpringBootTest
class EsBlogRepositoryTest {
    @Autowired
    EsBlogRepository esBlogRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    EsBlogSuggestRepository esBlogSuggestRepository;

    @Autowired
    HighLightResultMapper highLightResultMapper;

    @Test
    public void testIndex() {
        elasticsearchTemplate.createIndex(EsBlog.class);
    }

    @Test
    public void initRepository() {

        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlog("登鹳雀楼", "王之涣的登鹳雀楼", "白日依山尽，黄河入海流。欲穷千里目，更上一层楼。"));

        esBlogRepository.save(new EsBlog("相思", "王维的相思", "红豆生南国，春来发几枝。 愿君多采撷，此物最相思。"));

        esBlogRepository.save(new EsBlog("静夜思", "李白的静夜思", "床前明月光，疑是地上霜。\n" +
                "举头望明月，低头思故乡。"));

        esBlogRepository.save(new EsBlog("测试", "zhonghuarenmingongheguo", "中华人民公"));
    }

    @Test
    public void testFindDistinctByTitleContainingOrSummaryContainingOrContentContaining() {
        String title = "思";
        String summary = "相思";
        String content = "相思";
        Pageable pageable = PageRequest.of(0, 20);
        Page<EsBlog> page = esBlogRepository.findDistinctByTitleContainingOrSummaryContainingOrContentContaining
                (title, summary, content, pageable);
        Assertions.assertEquals(page.getTotalElements(), 2);

        page.getContent().forEach(System.out::println);
    }


    /**
     * 单个匹配-精确查询
     */
    @Test
    public void testTermQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("title", "相思"))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * 不设置查询条件-带分页
     */
    @Test
    public void testMatchAllQueryWithPage() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withPageable(PageRequest.of(0, 10))
                .build();
        Page<EsBlog> page = esBlogRepository.search(nativeSearchQuery);
        System.out.println(JSONObject.toJSONString(page));
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * 排序查询
     */
    @Test
    public void testQuerySort() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withSort(SortBuilders.fieldSort("_id").order(SortOrder.DESC))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * 模糊查询 包含"此物相思2"的分词结果中的一个或以上词的文档
     */
    @Test
    public void testMatchQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("content", "此物相思2"))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * 模糊查询 包含"此物相思"分词结果中所有词结果的文档
     */
    @Test
    public void testMatchPhraseQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("content", "此物相思"))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * 多字段模糊查询
     */
    @Test
    public void testMultiMatchQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("相思", "title", "content", "summary"))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * 字符串模糊查询 左右模糊查询
     */
    @Test
    public void testQueryStringQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("相思"))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * 前缀查询  如果字段没分词，就匹配整个字段前缀
     */
    @Test
    public void testPrefixQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.prefixQuery("title", "静"))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * wildcard query:通配符查询，支持* 任意字符串；？任意一个字符
     */
    @Test
    public void testWardQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("title", "静*"))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * fuzzy query:分词模糊查询，通过增加fuzziness模糊属性来查询,
     * 如能够匹配hotelName为tel前或后加一个字母的文档，fuzziness 的含义是检索的term 前后增加或减少n个单词的匹配查询
     */
    @Test
    public void testFuzzyQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.fuzzyQuery("content", "zhonghuarenmingongheguo"))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * 常用的用于推荐相似内容的查询
     */
    @Test
    public void testoreLikeThisQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.moreLikeThisQuery(new String[]{"敬业思"}))
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
    }

    /**
     * field 注解 中的fielddata属性设置为true
     */
    @Test
    public void testAggregation(){
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("aggregation").field("title");
        NativeSearchQuery nativeSearchQuery =new NativeSearchQueryBuilder()
                .addAggregation(aggregationBuilder)
                .build();

        Aggregations aggregations = elasticsearchTemplate.query(nativeSearchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse searchResponse) {
                return searchResponse.getAggregations();
            }
        });
        Map<String, Aggregation> asMap = aggregations.getAsMap();

        Terms terms  = (Terms) asMap.get("aggregation");
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            System.out.println(bucket.getKey().toString());
        }
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);

    }

    /**
     * QueryBuilders.boolQuery()
     * QueryBuilders.boolQuery().must();//文档必须完全匹配条件，相当于and
     * QueryBuilders.boolQuery().mustNot();//文档必须不匹配条件，相当于not
     * QueryBuilders.boolQuery().should();//至少满足一个条件，这个文档就符合should，相当于or
     */

    /**
     * 高亮显示
     */
    @Test
    public void testHighLight() {
        HighlightBuilder.Field field = new HighlightBuilder
                .Field("*")
                .preTags("<span>")
                .postTags("</span>");
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("content", "相思2"))
                .withHighlightFields(field)
                .build();
        List<EsBlog> list = elasticsearchTemplate.queryForList(nativeSearchQuery, EsBlog.class);
        list.forEach(System.out::println);
        highLightQuery(1,5,"ASC","_id",new String[]{"title"},"静夜思",EsBlog.class);
    }
    /**
     * 高亮查询
     *
     * @param searchFields
     * @param keyword
     * @return
     */
    public List<Class> highLightQuery(Integer page, Integer size, String sortType, String sortField,
                                         String[] searchFields, String keyword,Class clazz){
        Sort sort = null;
        if(StringUtils.isNotBlank(sortField)){
            sort = "ASC".equalsIgnoreCase(sortType) ? Sort.by(Sort.Direction.ASC, sortField) : Sort.by(Sort.Direction.DESC, sortField);
        }

        Pageable pageable = PageRequest.of(page-1, size ,sort);

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        searchQueryBuilder.withPageable(pageable);
        for(String searchField : searchFields){
            searchQueryBuilder.withQuery(QueryBuilders.matchQuery(searchField, keyword))
                    .withHighlightFields(new HighlightBuilder.Field(searchField)
                            //替换默认高亮标签<em></em>
                            .preTags("<span style=\"color:red\">").postTags("</span>"));

        }
        List list = elasticsearchTemplate.queryForList(searchQueryBuilder.build(), clazz);
        list.forEach(System.out::println);
        Page<Class> tls = elasticsearchTemplate.queryForPage(searchQueryBuilder.build(), clazz, highLightResultMapper);
        System.out.println(tls.getContent());
        return tls.getContent();
    }

    /**
     * 自动补全
     */
    @Test
    public void testSuggestCompletionProc() {

        String suggestField = "title";//指定在哪个字段搜索
        String suggestValue = "静";//输入的信息
        Integer suggestMaxCount = 10;//获得最大suggest条数

        CompletionSuggestionBuilder suggestionBuilderDistrict = new CompletionSuggestionBuilder(suggestField).prefix(suggestValue).size(suggestMaxCount);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("student_suggest", suggestionBuilderDistrict);//添加suggest

        //设置查询builder的index,type,以及建议
        SearchRequestBuilder requestBuilder = this.elasticsearchTemplate.getClient().prepareSearch("blog").setTypes("blog").suggest(suggestBuilder);
        System.out.println(requestBuilder.toString());

        SearchResponse response = requestBuilder.get();
        Suggest suggest = response.getSuggest();//suggest实体

        Set<String> suggestSet = new HashSet<>();//set
        int maxSuggest = 0;
        if (suggest != null) {
            Suggest.Suggestion result = suggest.getSuggestion("student_suggest");//获取suggest,name任意string
            for (Object term : result.getEntries()) {
                if (term instanceof CompletionSuggestion.Entry) {
                    CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;
                    if (!item.getOptions().isEmpty()) {
                        //若item的option不为空,循环遍历
                        for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
                            String tip = option.getText().toString();
                            if (!suggestSet.contains(tip)) {
                                suggestSet.add(tip);
                                ++maxSuggest;
                            }
                        }
                    }
                }
                if (maxSuggest >= suggestMaxCount) {
                    break;
                }
            }
        }
        List<String> suggests = Arrays.asList(suggestSet.toArray(new String[]{}));

        suggests.forEach((s) -> {
            System.out.println(s);
        });

    }

    @Test
    public void testCreateEsBlogSuggestIndex() {
        elasticsearchTemplate.createIndex(EsBlogSuggest.class);
    }

    @Test
    public void testUpdateSuggest() {
        esBlogSuggestRepository.deleteAll();
        EsBlog esBlog = new EsBlog();
        esBlog.setTitle("黄河之水天使来");

        AnalyzeRequestBuilder requestBuilder = new AnalyzeRequestBuilder(
                elasticsearchTemplate.getClient(), AnalyzeAction.INSTANCE, "blog_index", esBlog.getTitle());
        requestBuilder.setAnalyzer("ik_smart");

        AnalyzeResponse analyzeTokens = requestBuilder.get();
        List<AnalyzeResponse.AnalyzeToken> tokens = analyzeTokens.getTokens();
        Set<String> suggestSet = new HashSet<>();
        List<EsBlogSuggest> esBlogSuggests = new ArrayList<>();
        for (AnalyzeResponse.AnalyzeToken token : tokens) {
            String term = token.getTerm();
            if ("<NUM>".equals(token.getType()) || term.length() < 2) {
                continue;
            }
            if (!suggestSet.contains(term)) {
                suggestSet.add(term);
            }

        }

        long i = 0l;
        for (String str : suggestSet) {
            EsBlogSuggest esBlogSuggest = new EsBlogSuggest();
            esBlogSuggest.setId(++i);
            Completion completion = new Completion(new String[]{str});
            esBlogSuggest.setSuggesttag(completion);
            esBlogSuggest.setKeyword(esBlog.getTitle());
            esBlogSuggests.add(esBlogSuggest);
        }
        EsBlogSuggest esBlogSuggest = new EsBlogSuggest();
        esBlogSuggest.setId(++i);
        Completion completion = new Completion(new String[]{esBlog.getTitle()});
        esBlogSuggest.setSuggesttag(completion);
        esBlogSuggest.setKeyword(esBlog.getTitle());
        esBlogSuggests.add(esBlogSuggest);

        esBlogSuggestRepository.saveAll(esBlogSuggests);
    }

    @Test
    public void testSuggest() {
        String prefix = "黄";
        CompletionSuggestionBuilder suggestion = SuggestBuilders.completionSuggestion("suggesttag")
                .prefix(prefix).size(5);
        suggestion.analyzer("ik_smart");
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("autocomplete", suggestion);

        SearchRequestBuilder searchRequestBuilder = this.elasticsearchTemplate.getClient().prepareSearch("blog_index")
                .setTypes("blog_suggest").suggest(suggestBuilder);
        SearchResponse searchResponse = searchRequestBuilder.get();
        Suggest suggest = searchResponse.getSuggest();
        Suggest.Suggestion autocomplete = suggest.getSuggestion("autocomplete");
        int maxResultCount = 5;
        Set<String> result = new HashSet<>();
        for (Object o : autocomplete) {
            if (o instanceof CompletionSuggestion.Entry) {
                CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) o;
                if (item.getOptions().isEmpty()) {
                    continue;
                }
                for (CompletionSuggestion.Entry.Option option : item) {
                    String s = option.getText().toString();
                    if (result.contains(s)) {
                        continue;
                    }
                    result.add(s);
                    maxResultCount++;
                }
                if (maxResultCount > 5) {
                    break;
                }
            }
        }
        result.forEach(System.out::println);
    }
}