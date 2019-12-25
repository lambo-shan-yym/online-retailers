package com.lambo.onlineretailers.error;

import com.lambo.onlineretailers.common.ResponseCode;

/**
 * @ClassName: LamboException
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:32
 * @Version: 1.0
 */
public class LamboException extends RuntimeException {

    private int code;

    public LamboException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
    }

    public LamboException(ResponseCode responseCode, String msg) {
        super(String.format(responseCode.getMsg(), msg));
        this.code = responseCode.getCode();
    }

    public int getCode() {
        return code;
    }

}
