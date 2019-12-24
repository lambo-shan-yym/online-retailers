package com.lambo.onlineretailers.error;

import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.common.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: GlobeExceptionHandler
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:34
 * @Version: 1.0
 */
@ControllerAdvice
public class GlobeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServerResponse handlerExceptiop(Exception ex) {
        if (ex instanceof LamboException) {
            LamboException lamboException = (LamboException) ex;
            return ServerResponse.error(lamboException);
        }
        return ServerResponse.error(ResponseCode.SERVER_ERROR);
    }
}
