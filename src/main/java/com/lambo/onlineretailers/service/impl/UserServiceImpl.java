package com.lambo.onlineretailers.service.impl;

import com.lambo.onlineretailers.common.Constants;
import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.dao.UserRepository;
import com.lambo.onlineretailers.dto.UserDTO;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.enums.UserRoleEnum;
import com.lambo.onlineretailers.error.LamboException;
import com.lambo.onlineretailers.service.IUserService;
import com.lambo.onlineretailers.util.AssertUtil;
import com.lambo.onlineretailers.util.MD5Util;
import com.lambo.onlineretailers.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserDTO login(String username, String password) {
        checkValid(username,Constants.USERNAME,Constants.NOT_EXIST);
        password = MD5Util.MD5EncodeUtf8(password);
        User user=userRepository.findByUsernameAndPassword(username,password);
        if(user==null){
            throw new LamboException(ResponseCode.USER_USERNAME_PASSWORD_NOT_RIGHT);
        }
        UserDTO userDTO =new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setPassword(null);
        return userDTO;
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

}
