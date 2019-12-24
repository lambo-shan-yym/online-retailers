package com.lambo.onlineretailers.service.impl;

import com.lambo.onlineretailers.common.Constans;
import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.dao.UserRepository;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.error.LamboException;
import com.lambo.onlineretailers.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        checkValid(username,Constans.USERNAME);
        String question= userRepository.findQuestionByUsername(username);
        if(StringUtils.isBlank(question)){
            throw new LamboException(ResponseCode.USER_QUESTION_IS_BLANK);
        }
        return question;
    }

    @Override
    public String findByUsernameAndQuestionAndAnswer(String username,String question,String answer) {
        checkAnswer(username,question,answer);
        // todo
        String token = UUID.randomUUID().toString();
        return token;
    }

    public void checkAnswer(String username,String question,String answer){

        int count = userRepository.countByUsernameAndQuestionAndAnswer(username,question,answer);
        if(count==0){
            throw new LamboException(ResponseCode.USER_ANSWER_NOT_RIGHT);
        }
    }

    public void checkValid(String value,String type){
        if(Constans.USERNAME.equals(type)){
            checkUsernameExist(value);
        }else if(Constans.EMAIL.equals(type)){
            checkEmailExist(value);
        }

    }

    public void checkUsernameExist(String username){
        int count = userRepository.countByUsername(username);
        if(count==0){
            throw new LamboException(ResponseCode.USER_NOT_EXIST);
        }
    }

    public void checkEmailExist(String email){
        int count = userRepository.countByEmail(email);
        if(count==0){
            throw new LamboException(ResponseCode.USER_EMAIL_NOT_EXIST);
        }
    }
}
