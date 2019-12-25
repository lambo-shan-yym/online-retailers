package com.lambo.onlineretailers.util;

import com.lambo.onlineretailers.common.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CookieUtil
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/25 13:13
 * @Version: 1.0
 */
public class CookieUtil {
    public static  void setToken(HttpServletResponse response,String token){
        Cookie cookie =new Cookie(Constants.COOKIE_TOKEN, token);
        cookie.setPath("/");
        cookie.setMaxAge(Constants.COOKIE_TOKEN_EXPIRE);
        response.addCookie(cookie);
    }

    public static  void removeToken(HttpServletResponse response){
        Cookie cookie =new Cookie(Constants.COOKIE_TOKEN, UUIDUtil.uuid());
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
