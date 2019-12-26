package com.lambo.onlineretailers.common;

/**
 * @ClassName: ResponseCode
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:26
 * @Version: 1.0
 */
public enum ResponseCode {

    SUCCESS(0, "success"),

    REQUIRE_ARGUMENT(400, "缺少%s参数"),
    INVALID_ARGUMENT(400, "参数错误:%s"),
    SERVER_ERROR(5000000, "服务器未知错误"),

    // 用户模块50100
    USER_USERNAME_NOT_EXIST(5010001, "用户名不存在"),
    USER_EMAIL_HAS_EXIST(5010002, "用户邮箱已存在"),
    USER_USERNAME_HAS_EXIST(5010003, "用户名已存在"),
    USER_PASSWORD_NOT_RIGHT(5010004, "密码不正确"),
    USER_EMAIL_NOT_EXIST(5010005, "邮箱不存在"),
    USER_QUESTION_IS_BLANK(5010006,"重置密码的问题是空的"),

    USER_ANSWER_NOT_RIGHT(5010007,"问题的答案错误" ),
    USER_USERNAME_PASSWORD_NOT_RIGHT(5010008, "密码不正确"),
    USER_HAS_NOT_LOGIN(5010009,"用户未登录"),
    USER_TOKEN_HAS_EXPIRE(5010010,"用户token失效,请重新登录"),
    USER_PASSWORD_OLD_NEW_NO_EQUAL(5010011, "新密码不能和旧密码一致"),

    CATEGORY_NOT_EXIST(5020001, "商城分类不存在"),
    AUTH_DENIED(403, "授权受限（无权限或IP地址受限等）"),


    SHIPPING_NOT_EXIST(5030001,"收货地址不存在"),

    ;
    private int code;

    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
