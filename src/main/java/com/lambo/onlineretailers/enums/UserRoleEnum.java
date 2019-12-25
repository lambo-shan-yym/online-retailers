package com.lambo.onlineretailers.enums;

/**
 * @author lambo
 * 用户角色枚举类
 */
public enum UserRoleEnum {
    NORMAL_USER(1),
    ADMIN(2)
    ;
    int roleCode;

    UserRoleEnum(int roleCode) {
        this.roleCode = roleCode;
    }

    public int getRoleCode() {
        return roleCode;
    }
}
