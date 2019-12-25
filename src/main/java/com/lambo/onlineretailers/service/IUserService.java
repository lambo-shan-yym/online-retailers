package com.lambo.onlineretailers.service;

import com.lambo.onlineretailers.dto.UserDTO;
import com.lambo.onlineretailers.entity.User;

/**
 * @ClassName: IUserService
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:17
 * @Version: 1.0
 */
public interface IUserService {
    User save(User user);

    String findQuestionByUsername(String username);

    public String checkAnswer(String username, String question, String answer);

    UserDTO login(String username, String password);

    public UserDTO register(UserDTO userDTO);

    public UserDTO findByUsername(String username);


}
