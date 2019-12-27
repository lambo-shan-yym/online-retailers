package com.lambo.onlineretailers.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * @ClassName: InSpecification
 * @Author: yym
 * @Description: In动态查询条件InSpecification
 * @Date: 2019/12/27 14:03
 * @Version: 1.0
 */

public class InSpecification<T, ATTR> implements Specification<T>
{
    private String attrName;
    private ATTR[] values;
    public InSpecification(String attrName, ATTR[] values) {
        this.attrName = attrName;
        this.values = values;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<ATTR> path = SpecificationHelper.getPath(root, attrName);
        CriteriaBuilder.In<ATTR> predicate = cb.in(path);
        for(ATTR item : values) {
            predicate.value(item);
        }
        return predicate;
    }
}