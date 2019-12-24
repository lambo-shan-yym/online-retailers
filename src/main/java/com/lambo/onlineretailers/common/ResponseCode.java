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
    SERVER_ERROR(50000,"服务器未知错误");

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
