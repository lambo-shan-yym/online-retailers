package com.lambo.onlineretailers.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "")
    public ServerResponse add() {
        return ServerResponse.success("success");
    }


    @GetMapping(value = "forget_get_question")
    public ServerResponse forgetGetQuestion(@RequestParam("username") String username){
        ServerResponse serverResponse =ServerResponse.success(userService.findQuestionByUsername(username));
        return serverResponse;
    }

    @PostMapping(value = "forget_check_answer")
    public ServerResponse forgetCheckAnswer(@RequestBody JSONObject jsonObject){
        String username =jsonObject.getString("username");
        String question =jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        return ServerResponse.success(userService.findByUsernameAndQuestionAndAnswer(
                username,question,answer));
    }
}


