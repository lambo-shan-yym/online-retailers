package com.lambo.onlineretailers.controller.c;

import com.alibaba.fastjson.JSONObject;
import com.lambo.onlineretailers.common.Constants;
import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.dto.UserDTO;
import com.lambo.onlineretailers.error.LamboException;
import com.lambo.onlineretailers.service.IUserService;
import com.lambo.onlineretailers.util.AssertUtil;
import com.lambo.onlineretailers.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserController
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/v0.1/c/user/")
public class UserCController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/register")
    public ServerResponse register(@Validated @RequestBody UserDTO userDTO) {
        return ServerResponse.success(userService.register(userDTO));
    }

    @PostMapping(value = "/login")
    public ServerResponse login(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        AssertUtil.isEmpty(username, "username");
        AssertUtil.isEmpty(password, "password");

        return ServerResponse.success(userService.login(username, password, response));
    }

    @GetMapping(value = "/logout")
    public ServerResponse logout(
            @CookieValue(value = Constants.COOKIE_TOKEN, required = false) String token,
            HttpServletResponse response, UserDTO userDTO) {
        userService.logout(response, token);
        return ServerResponse.success();
    }

    //登录后获取用户信息
    @RequestMapping(value = "get_user_info", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<UserDTO> getUserInfo(HttpServletRequest request, UserDTO userDTO) {
        return ServerResponse.success(userDTO);

    }
    //登录状态的重置密码
    @RequestMapping(value = "reset_password", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> restPassword(HttpServletRequest request,HttpServletResponse response,
                                               UserDTO userDTO, @RequestBody JSONObject jsonObject) {

        String passwordOld = jsonObject.getString("password_old");
        String passwordNew = jsonObject.getString("password_new");
        AssertUtil.isEmpty(passwordOld, "password_old");
        AssertUtil.isEmpty(passwordNew, "password_new");
        userService.resetPassword(request,response,userDTO, passwordOld, passwordNew);
        return ServerResponse.success();
    }
    //提交问题答案
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return ServerResponse.success(userService.checkAnswer(username, question, answer));
    }

    @GetMapping(value = "forget_get_question")
    public ServerResponse forgetGetQuestion(@RequestParam("username") String username) {
        AssertUtil.isEmpty(username, "username");
        return ServerResponse.success(userService.findQuestionByUsername(username));
    }
}


