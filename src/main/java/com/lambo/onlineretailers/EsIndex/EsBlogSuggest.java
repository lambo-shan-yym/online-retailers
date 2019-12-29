package com.lambo.onlineretailers.EsIndex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.completion.Completion;

/**
 * @author lambo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "blog_index",type ="blog_suggest" )
public class EsBlogSuggest {
    @Field(type= FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String keyword;

    @CompletionField(analyzer="ik_smart",searchAnalyzer="ik_smart")
    private Completion suggesttag;
}
