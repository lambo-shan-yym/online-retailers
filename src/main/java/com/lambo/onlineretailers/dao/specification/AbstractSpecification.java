package com.lambo.onlineretailers.dao.specification;

import org.springframework.data.jpa.domain.Specification;

/**
 * @ClassName: AbstractSpecification
 * @Author: yym
 * @Description: AbstractSpecification（抽象动态查询条件）
 * @Date: 2019/12/27 13:43
 * @Version: 1.0
 */

public abstract class AbstractSpecification<T, ATTR> implements Specification<T> {
    protected String attrName;
    protected ATTR attrValue;
    protected ATTR[] attrValues;
    protected int logicalOperator;
    /**
     * 构造方法
     * @param attrName			属性名称
     * @param attrValue			属性值
     * @param logicalOperator	逻辑运算符
     */
    public AbstractSpecification(String attrName, ATTR attrValue, int logicalOperator) {
        super();
        this.attrName = attrName;
        this.attrValue = attrValue;
        this.logicalOperator = logicalOperator;
    }

    public AbstractSpecification(String attrName, int logicalOperator, ATTR ... attrValues) {
        super();
        this.attrName = attrName;
        this.attrValues = attrValues;
        if (this.attrValues.length > 0) {
            this.attrValue = this.attrValues[0];
        }
        this.logicalOperator = logicalOperator;
    }

}
