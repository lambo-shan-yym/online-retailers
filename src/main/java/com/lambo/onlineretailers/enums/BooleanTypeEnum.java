package com.lambo.onlineretailers.enums;

/**
 * <p>Title:  boolean类型枚举/p>
 * <p>Description: BooleanTypeEnum</p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Co., Ltd.  </p>
 * <p>Create Time: 2016/1/28 </p>
 * @author yym
 */
public enum BooleanTypeEnum {
    TRUE("true"),
    FALSE("false");
    
    private String code;
    private BooleanTypeEnum(String code) {
        this.code = code;
    }
    public String getKey() {
        return this.code;
    }
}
