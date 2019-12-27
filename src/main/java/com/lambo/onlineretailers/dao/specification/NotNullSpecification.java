package com.lambo.onlineretailers.dao.specification;

import com.lambo.onlineretailers.dao.SpecificationOperation;

import javax.persistence.criteria.*;

/**
 * @ClassName: NotNullSpecification
 * @Author: yym
 * @Description: 不为空动态查询条件NotNullSpecification
 * @Date: 2019/12/27 14:06
 * @Version: 1.0
 */
public class NotNullSpecification<T> extends AbstractSpecification<T,Object> {
    public NotNullSpecification(String attrName) {
        super(attrName, null, SpecificationOperation.LOGICAL_OPERATOR_CUSTOM);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<Object> path = SpecificationHelper.getPath(root, attrName);

        return cb.isNotNull(path);
    }
}
