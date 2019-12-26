package com.lambo.onlineretailers.controller.c;

import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CategoryMController
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/26 11:11
 * @Version: 1.0
 */
@RestController
@RequestMapping("/v0.1/c/category/")
public class CategoryCController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/{id:\\d+}")
    public ServerResponse findById(@RequestParam(name = "id") Integer id, User user){
        return ServerResponse.success(categoryService.findById(id));
    }


}
