package com.lambo.onlineretailers.controller.c;

import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/v0.1/c/user/")
class UserCController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "")
    public ServerResponse add() {
        return ServerResponse.success("success");
    }
}


