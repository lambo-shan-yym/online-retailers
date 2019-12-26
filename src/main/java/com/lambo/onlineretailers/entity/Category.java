package com.lambo.onlineretailers.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Category
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:00
 * @Version: 1.0
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    private String name;

    private String status;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;
}
