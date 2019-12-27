package com.lambo.onlineretailers.dao.specification;

import com.lambo.onlineretailers.dao.SpecificationOperation;

/**
 * @ClassName: LikeSpecification
 * @Author: yym
 * @Description: Like动态查询条件LikeSpecification
 * @Date: 2019/12/27 14:04
 * @Version: 1.0
 */
public class LikeSpecification<T> extends StringSpecification<T> {

    public LikeSpecification(String attrName, String attrValue) {
        super(attrName, attrValue, SpecificationOperation.LOGICAL_OPERATOR_LIKE);
    }

}
