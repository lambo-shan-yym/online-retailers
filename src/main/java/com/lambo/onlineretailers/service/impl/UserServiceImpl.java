package com.lambo.onlineretailers.service.impl;

import com.lambo.onlineretailers.dao.UserRepository;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.service.IUserService;
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
}
