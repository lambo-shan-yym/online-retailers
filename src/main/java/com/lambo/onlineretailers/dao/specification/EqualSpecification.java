package com.lambo.onlineretailers.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * @ClassName: EqualSpecification
 * @Author: yym
 * @Description: 相等动态查询条件
 * @Date: 2019/12/27 14:03
 * @Version: 1.0
 */

public class EqualSpecification<T, ATTR> implements Specification<T>
{
    private String fieldName;
    private ATTR fieldValue;

    public EqualSpecification(String fieldName, ATTR fieldValue) {
        super();
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<ATTR> path = SpecificationHelper.getPath(root, fieldName);
        return cb.equal(path, fieldValue);
    }

}
