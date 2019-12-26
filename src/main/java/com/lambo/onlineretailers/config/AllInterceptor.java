package com.lambo.onlineretailers.config;

import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.dto.UserDTO;
import com.lambo.onlineretailers.enums.UserRoleEnum;
import com.lambo.onlineretailers.error.LamboException;
import com.lambo.onlineretailers.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AllInterceptor
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/26 11:31
 * @Version: 1.0
 */
@Component
public class AllInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // 判定是否需要校验
        if (this.isNotCheck(request)) {
            return true;
        }
        UserDTO userDTO = userService.getUserByToken(request,response);
        String uri =request.getServletPath();
        if(uri.contains("/m/")&& UserRoleEnum.ADMIN.getRoleCode()!=userDTO.getRole()){
            throw new LamboException(ResponseCode.AUTH_DENIED);
        }
        if(uri.contains("/c/")&& UserRoleEnum.NORMAL_USER.getRoleCode()!=userDTO.getRole()){
            throw new LamboException(ResponseCode.AUTH_DENIED);
        }
        return true;
    }

    /**
     * 是否需要校验标识信息
     * @author liujun
     * @param request
     * @return boolean 需要检验服务标识信息返回true， 否则返回false
     */
    private boolean isNotCheck(HttpServletRequest request) {
        String path =request.getServletPath();
        if(path.matches("^/(v.*|.*)/m/user/(login)")){
            return true;
        }
        if(path.matches("^/(v.*|.*)/c/user/(login|register)")){
            return true;
        }
        return false;
    }
}
