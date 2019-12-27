package com.lambo.onlineretailers.dao;

/**
 * @ClassName: SpecificationOperation
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/27 14:13
 * @Version: 1.0
 */
public interface SpecificationOperation {
    /**
     * 逻辑运算：等于
     */
    public static final int LOGICAL_OPERATOR_EQUAL = 1;
    /**
     * 逻辑运算：小于
     */
    public static final int LOGICAL_OPERATOR_LESS = 2;
    /**
     * 逻辑运算：大于
     */
    public static final int LOGICAL_OPERATOR_GREATER = 3;
    /**
     * 逻辑运算：小于等于
     */
    public static final int LOGICAL_OPERATOR_LESS_EQUAL = 4;
    /**
     * 逻辑运算：大于等于
     */
    public static final int LOGICAL_OPERATOR_GREATER_EQUAL = 5;
    /**
     * 逻辑运算：不等于
     */
    public static final int LOGICAL_OPERATOR_NOT_EQUAL = 6;
    /**
     * 逻辑运算：包含
     */
    public static final int LOGICAL_OPERATOR_LIKE = 7;
    /**
     * 逻辑运算：左包含
     */
    public static final int LOGICAL_OPERATOR_START_WITH = 8;
    /**
     * 逻辑运算：右包含
     */
    public static final int LOGICAL_OPERATOR_END_WITH = 9;

    /**
     * 逻辑运算：非空
     */
    public static final int LOGICAL_OPERATOR_NOT_NULL = 10;
    /**
     * 逻辑运算：In
     */
    public static final int LOGICAL_OPERATOR_IN = 11;
    /**
     * 逻辑运算：包含（自定义）
     */
    public static final int LOGICAL_OPERATOR_LIKE_CUSTOM = 99;
    public static final int LOGICAL_OPERATOR_CUSTOM = 100;
}
