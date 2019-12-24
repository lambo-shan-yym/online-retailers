package com.lambo.onlineretailers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: Order
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:26
 * @Version: 1.0
 */
@Entity
@Table(name = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_no")
    private Long orderNo;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "shipping_id")
    private Integer shippingId;

    private BigDecimal payment;

    @Column(name = "payment_type")
    private Integer paymentType;

    private Integer postage;

    private Integer status;
    @Column(name = "payment_time")
    private Date paymentTime;
    @Column(name = "send_time")
    private Date sendTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "close_time")
    private Date closeTime;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
