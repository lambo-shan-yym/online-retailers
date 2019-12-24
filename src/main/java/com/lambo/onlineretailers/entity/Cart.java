package com.lambo.onlineretailers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: Cart
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:35
 * @Version: 1.0
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    private Integer quantity;

    private Integer checked;

    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
