package com.lambo.onlineretailers.dto;

import com.lambo.onlineretailers.validator.InsertValidator;
import com.lambo.onlineretailers.validator.UpdateValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName: Shipping
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:40
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDTO {

    private Integer id;
    private Integer userId;
    @NotBlank(message = "不能为空",groups = {InsertValidator.class})
    private String receiverName;
    @NotBlank(message = "不能为空",groups = {InsertValidator.class})
    private String receiverPhone;
    @NotBlank(message = "不能为空",groups = {InsertValidator.class})
    private String receiverMobile;
    @NotBlank(message = "不能为空",groups = {InsertValidator.class})
    private String receiverProvince;
    @NotBlank(message = "不能为空",groups = {InsertValidator.class})
    private String receiverCity;
    @NotBlank(message = "不能为空",groups = {InsertValidator.class})
    private String receiverDistrict;
    @NotBlank(message = "不能为空",groups = {InsertValidator.class})
    private String receiverAddress;
    @NotBlank(message = "不能为空",groups = {InsertValidator.class})
    private String receiverZip;

}
