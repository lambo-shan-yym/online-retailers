package com.lambo.onlineretailers.dto.query;

import lombok.Data;

/**
 * @ClassName: ProductParam
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/27 10:51
 * @Version: 1.0
 */
@Data
public class ProductParam {
    private String keyword;
    private Integer status;
    private Integer categoryId;
}
