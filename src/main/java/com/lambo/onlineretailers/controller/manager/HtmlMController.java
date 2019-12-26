package com.lambo.onlineretailers.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lambo
 */
@Controller
@RequestMapping(value = "/m")
public class HtmlMController {

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
