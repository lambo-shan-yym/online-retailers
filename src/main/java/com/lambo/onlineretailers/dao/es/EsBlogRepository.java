package com.lambo.onlineretailers.dao.es;

import com.lambo.onlineretailers.EsIndex.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author lambo
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog,String> {
    /**
     * 分页查询博客信息(去重)
     * @param title
     * @param summary
     * @param content
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);

}
