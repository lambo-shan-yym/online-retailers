package com.lambo.onlineretailers.entity;

import com.lambo.onlineretailers.enums.ProductStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: Product
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:03
 * @Version: 1.0
 */
@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    private String name;

    private String subtitle;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "sub_images")
    private String subImages;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status = ProductStatusEnum.OFF.getCode();
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;
    @Column(name = "update_time")
    @UpdateTimestamp
    private Date updateTime;
}
