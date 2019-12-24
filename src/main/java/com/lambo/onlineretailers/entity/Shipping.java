package com.lambo.onlineretailers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: Shipping
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:40
 * @Version: 1.0
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "receiver_phone")
    private String receiverPhone;
    @Column(name = "receiver_mobile")
    private String receiverMobile;
    @Column(name = "receiver_province")
    private String receiverProvince;
    @Column(name = "receiver_city")
    private String receiverCity;
    @Column(name = "receiver_district")
    private String receiverDistrict;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "receiver_zip")
    private String receiverZip;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
