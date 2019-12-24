package com.lambo.onlineretailers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
