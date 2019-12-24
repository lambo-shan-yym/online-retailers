package com.lambo.onlineretailers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: OrderItem
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:32
 * @Version: 1.0
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_no")
    private Long orderNo;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "current_unit_price")
    private BigDecimal currentUnitPrice;
    private Integer quantity;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
