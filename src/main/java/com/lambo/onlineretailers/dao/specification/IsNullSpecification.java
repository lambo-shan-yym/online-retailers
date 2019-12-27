package com.lambo.onlineretailers.dao.specification;

import com.lambo.onlineretailers.dao.SpecificationOperation;

import javax.persistence.criteria.*;

/**
 * @ClassName: IsNullSpecification
 * @Author: yym
 * @Description: 判空查询条件IsNullSpecification
 * @Date: 2019/12/27 14:05
 * @Version: 1.0
 */
public class IsNullSpecification<T> extends AbstractSpecification<T,Object> {
    public IsNullSpecification(String attrName) {
        super(attrName, null, SpecificationOperation.LOGICAL_OPERATOR_CUSTOM);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<Object> path = SpecificationHelper.getPath(root, attrName);

        return cb.isNull(path);
    }
}