package com.lambo.onlineretailers.dao.es;

import com.lambo.onlineretailers.EsIndex.EsBlogSuggest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author lambo
 */
public interface EsBlogSuggestRepository extends ElasticsearchRepository<EsBlogSuggest,Long> {
}
