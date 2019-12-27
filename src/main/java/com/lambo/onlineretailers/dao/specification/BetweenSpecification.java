package com.lambo.onlineretailers.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * @ClassName: BetweenSpecification
 * @Author: yym
 * @Description: Between动态查询条件BetweenSpecification
 * @Date: 2019/12/27 14:02
 * @Version: 1.0
 */
public class BetweenSpecification<T, ATTR extends Comparable<ATTR>> implements Specification<T> {
    private String attrName;
    private ATTR lowerBound, upperBound;

    /**
     * 构造方法
     * @param attrName		属性名称
     * @param lowerBound	属性值下界
     * @param upperBound	属性值上界
     */
    public BetweenSpecification(String attrName, ATTR lowerBound, ATTR upperBound) {
        super();
        this.attrName = attrName;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<ATTR> path = SpecificationHelper.getPath(root, attrName);
        return cb.between(path, lowerBound, upperBound);
    }

}
