package com.lambo.onlineretailers.service.impl;

import com.lambo.onlineretailers.common.Constants;
import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.dao.UserRepository;
import com.lambo.onlineretailers.dto.UserDTO;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.enums.UserRoleEnum;
import com.lambo.onlineretailers.error.LamboException;
import com.lambo.onlineretailers.redis.RedisService;
import com.lambo.onlineretailers.redis.UserKey;
import com.lambo.onlineretailers.service.IUserService;
import com.lambo.onlineretailers.util.AssertUtil;
import com.lambo.onlineretailers.util.CookieUtil;
import com.lambo.onlineretailers.util.MD5Util;
import com.lambo.onlineretailers.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserServiceImpl
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:18
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RedisService redisService;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String findQuestionByUsername(String username) {
        String question= userRepository.findQuestionByUsername(username);
        if(StringUtils.isBlank(question)){
            throw new LamboException(ResponseCode.USER_QUESTION_IS_BLANK);
        }
        return question;
    }

    @Override
    public String checkAnswer(String username,String question,String answer) {
        int count = userRepository.countByUsernameAndQuestionAndAnswer(username,question,answer);
        if(count==0){
            throw new LamboException(ResponseCode.USER_ANSWER_NOT_RIGHT);
        }
        String forgetToken = UUIDUtil.uuid();
        // todo 放入缓存中
        return forgetToken;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserDTO login(String username, String password, HttpServletResponse response) {
        checkValid(username,Constants.USERNAME,Constants.NOT_EXIST);
        password = MD5Util.MD5EncodeUtf8(password);
        User user=userRepository.findByUsernameAndPassword(username,password);
        if(user==null){
            throw new LamboException(ResponseCode.USER_USERNAME_PASSWORD_NOT_RIGHT);
        }
        UserDTO userDTO =new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setPassword(null);
        String token =UUIDUtil.uuid();
        setTokenAndAddCookie(response, userDTO, token);
        return userDTO;
    }
    public UserDTO getUserByToken(HttpServletRequest request,HttpServletResponse response){
        String token = getCookieValue(request,Constants.COOKIE_TOKEN);
        if(StringUtils.isBlank(token)){
            throw new LamboException(ResponseCode.USER_HAS_NOT_LOGIN);
        }
        UserDTO userDTO = redisService.get(UserKey.TOKEN, token, UserDTO.class);
        if(userDTO!=null){
            setTokenAndAddCookie(response,userDTO,token);
        }else {
            throw new LamboException(ResponseCode.USER_TOKEN_HAS_EXPIRE);
        }
        return userDTO;
    }

    @Override
    public void resetPassword(HttpServletRequest request,HttpServletResponse response,
                              UserDTO userDTO, String passwordOld, String passwordNew) {

        String passwordOldMd5 = MD5Util.MD5EncodeUtf8(passwordOld);
        String passwordNewMD5 = MD5Util.MD5EncodeUtf8(passwordNew);
        if(passwordOldMd5.equals(passwordNewMD5)){
            throw new LamboException(ResponseCode.USER_PASSWORD_OLD_NEW_NO_EQUAL);
        }
        User user = userRepository.findByUsername(userDTO.getUsername());

        if(!user.getPassword().equals(passwordOldMd5)){
            throw new LamboException(ResponseCode.USER_PASSWORD_NOT_RIGHT);
        }
        user.setPassword(passwordNewMD5);
        userRepository.save(user);
        String token = getCookieValue(request,Constants.COOKIE_TOKEN);
        removeTokenAndCookie(response,token);
    }

    /**
     * 设置token缓存 添加cookie
     * @param response
     * @param userDTO
     * @param token
     */
    private void setTokenAndAddCookie(HttpServletResponse response, UserDTO userDTO, String token) {
        // 设置缓存
        redisService.set(UserKey.TOKEN,token,userDTO);
        // 设置cookie
        Cookie cookie =new Cookie(Constants.COOKIE_TOKEN, token);
        cookie.setPath("/");
        cookie.setMaxAge(Constants.COOKIE_TOKEN_EXPIRE);
        response.addCookie(cookie);
    }


    /**
     * 注册用户
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO register(UserDTO userDTO) {
        checkValid(userDTO.getUsername(),Constants.USERNAME,Constants.EXIST);
        checkValid(userDTO.getEmail(),Constants.EMAIL,Constants.EXIST);
        userDTO.setRole(UserRoleEnum.NORMAL_USER.getRoleCode());
        userDTO.setPassword(MD5Util.MD5EncodeUtf8(userDTO.getPassword()));
        User user =new User();
        BeanUtils.copyProperties(userDTO,user);
        BeanUtils.copyProperties(userRepository.save(user),userDTO);
        return userDTO;
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserDTO userDTO =new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public void logout(HttpServletResponse response,String token) {
        removeTokenAndCookie(response, token);
    }

    private void removeTokenAndCookie(HttpServletResponse response, String token) {
        // 删除缓存
        redisService.delete(UserKey.TOKEN,token);
        // 设置cookie过期时间为0
        Cookie cookie =new Cookie(Constants.COOKIE_TOKEN, token);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


    public void checkValid(String value,String type,int exceptionType){
        AssertUtil.isEmpty(type,"type");
        if(Constants.USERNAME.equals(type)){
            checkUsernameExist(value,exceptionType);
        }else if(Constants.EMAIL.equals(type)){
            checkEmailExist(value,exceptionType);
        }

    }

    public void checkUsernameExist(String username,int exceptionType){
        int count = userRepository.countByUsername(username);
        if(Constants.EXIST==exceptionType&&count>0){
            throw new LamboException(ResponseCode.USER_USERNAME_HAS_EXIST);
        }
        if(Constants.NOT_EXIST==exceptionType&&count==0){
            throw new LamboException(ResponseCode.USER_USERNAME_NOT_EXIST);
        }
    }

    public void checkEmailExist(String email,int exceptionType){
        int count = userRepository.countByEmail(email);
        if(Constants.EXIST==exceptionType&&count>0){
            throw new LamboException(ResponseCode.USER_EMAIL_HAS_EXIST);
        }
        if(Constants.NOT_EXIST==exceptionType&&count==0){
            throw new LamboException(ResponseCode.USER_EMAIL_NOT_EXIST);
        }
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies =request.getCookies();
        if(cookies==null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if(cookieName.equals(cookie.getName()))
                return cookie.getValue();
        }
        return null;
    }
}
