package com.lambo.onlineretailers.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lambo.onlineretailers.error.LamboException;

/**
 * @ClassName: ServerResponse
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:22
 * @Version: 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {

    private int code;

    private String msg;

    private T data;

    public ServerResponse() {
    }

    private ServerResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    private ServerResponse(T data) {
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<>(data);
    }

    public static ServerResponse error(ResponseCode responseCode) {
        return new ServerResponse(responseCode);
    }
    public static ServerResponse error(LamboException ex) {
        return new ServerResponse(ex.getResponseCode());
    }
}