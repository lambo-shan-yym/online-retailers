package com.lambo.onlineretailers.controller.c;

import com.lambo.onlineretailers.common.ServerResponse;
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
@RequestMapping("/v0.1/c/user/")
public class UserCController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "")
    public ServerResponse add() {
        return ServerResponse.success("success");
    }

    @GetMapping(value = "forget_get_question")
    public ServerResponse forgetGetQuestion(@RequestParam("username") String username){
        return ServerResponse.success(userService.findQuestionByUsername(username));
    }
}


