package com.lambo.onlineretailers.dao.specification;

import com.lambo.onlineretailers.dao.SpecificationOperation;

import javax.persistence.criteria.*;

/**
 * @ClassName: NumberSpecification
 * @Author: yym
 * @Description: 数字属性查询条件NumberSpecification
 * @Date: 2019/12/27 14:07
 * @Version: 1.0
 */
public class NumberSpecification<T> extends AbstractSpecification<T, Number> {

    public NumberSpecification(String attrName, Number attrValue, int logicalOperator) {
        this(attrName, logicalOperator, attrValue);
    }

    public NumberSpecification(String attrName, int logicalOperator, Number... attrValues) {
        super(attrName, logicalOperator, attrValues);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<Number> path = SpecificationHelper.getPath(root, attrName);
        switch (logicalOperator) {
            case SpecificationOperation.LOGICAL_OPERATOR_EQUAL: {
                return cb.equal(path, attrValue);
            }
            case SpecificationOperation.LOGICAL_OPERATOR_GREATER: {
                return cb.gt(path, attrValue);
            }
            case SpecificationOperation.LOGICAL_OPERATOR_GREATER_EQUAL: {
                return cb.ge(path, attrValue);
            }
            case SpecificationOperation.LOGICAL_OPERATOR_LESS: {
                return cb.lt(path, attrValue);
            }
            case SpecificationOperation.LOGICAL_OPERATOR_LESS_EQUAL: {
                return cb.le(path, attrValue);
            }
            case SpecificationOperation.LOGICAL_OPERATOR_NOT_EQUAL: {
                return cb.notEqual(path, attrValue);
            }
            case SpecificationOperation.LOGICAL_OPERATOR_IN: {
                CriteriaBuilder.In<Number> predicate = cb.in(path);
                for (Number item : attrValues) {
                    predicate.value(item);
                }
                return predicate;
            }
            default:
                return null;
        }
    }
}