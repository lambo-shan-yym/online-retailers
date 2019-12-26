package com.lambo.onlineretailers.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.lambo.onlineretailers.common.Constants;
import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.dto.UserDTO;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.service.IUserService;
import com.lambo.onlineretailers.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserController
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/v0.1/m/user/")
public class UserMController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login")
    public ServerResponse login(@RequestBody UserDTO userDTO, HttpServletResponse response) {

        return ServerResponse.success(userService.login(userDTO.getUsername(), userDTO.getPassword(), response));
    }

    @GetMapping(value = "/logout")
    public ServerResponse logout(
            @CookieValue(value = Constants.COOKIE_TOKEN, required = false) String token,
            HttpServletResponse response, UserDTO userDTO) {
        userService.logout(response, token);
        return ServerResponse.success();
    }
}


