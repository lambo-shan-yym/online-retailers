package com.lambo.onlineretailers.EsIndex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author lambo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "blog",type = "blog",useServerConfiguration = false) //文档标识
public class EsBlog implements Serializable {

    @Id //主键标识
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_smart",searchAnalyzer = "ik_smart")

    private String content;
    @Field(type = FieldType.Text, analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String summary;

    public EsBlog(String title, String content, String summary) {
        this.title = title;
        this.content = content;
        this.summary = summary;
    }
}
