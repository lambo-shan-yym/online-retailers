package com.lambo.onlineretailers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lambo.onlineretailers.validator.InsertValidator;
import com.lambo.onlineretailers.validator.UpdateValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName: Product
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:03
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id;
    @JsonProperty("category_id")
    @NotNull(message = "商品分类id不能为空",groups = {InsertValidator.class})
    private Integer categoryId;
    @NotEmpty(message = "商品名称不能为空",groups = {InsertValidator.class})
    private String name;

    private String subtitle;

    private String mainImage;

    private String subImages;

    private String detail;
    @NotNull(message = "价格不能为空",groups = {InsertValidator.class})
    @Digits(integer = 6, fraction = 2,groups = {InsertValidator.class, UpdateValidator.class})
    private BigDecimal price;
    @NotNull(message = "库存不能为空",groups = {InsertValidator.class})
    private Integer stock;
    private Integer status;
}
