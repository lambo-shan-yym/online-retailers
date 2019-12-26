package com.lambo.onlineretailers.controller.c;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: HtmlController
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/25 14:04
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/c")
public class HtmlController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/member_user")
    public String memberUser() {
        return "member_user";
    }
}
