package com.lambo.onlineretailers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: PayInfo
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:37
 * @Version: 1.0
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayInfo {

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "order_no")
    private Long orderNo;
    @Column(name = "pay_platform")
    private Integer payPlatform;
    @Column(name = "platform_number")
    private String platformNumber;
    @Column(name = "platform_status")
    private String platformStatus;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
