package com.lambo.onlineretailers.error;

import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
@Slf4j
public class GlobeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServerResponse handlerException(Exception ex) {
        if (ex instanceof LamboException) {
            LamboException lamboException = (LamboException) ex;
            return ServerResponse.error(lamboException);
        }
        if( ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException =  (MethodArgumentNotValidException) ex;
            String msg=methodArgumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
            return ServerResponse.error(ResponseCode.INVALID_ARGUMENT.getCode(),msg);
        }
        log.error("【服务器发送异常】:{}",ex.getMessage());
        ex.printStackTrace();
        return ServerResponse.error(ResponseCode.SERVER_ERROR);
    }
}
