package com.lambo.onlineretailers.controller.c;

import com.lambo.onlineretailers.annotation.NeedPage;
import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.dto.query.ProductParam;
import com.lambo.onlineretailers.enums.ProductStatusEnum;
import com.lambo.onlineretailers.page.SystemRequestHolder;
import com.lambo.onlineretailers.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ProductMController
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/27 10:56
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/v0.1/c/product")
public class ProductCController {
    @Autowired
    IProductService productService;


    @GetMapping(value = "/{id:\\d+}")
    public ServerResponse get(@PathVariable(name = "id") Integer id){
        return ServerResponse.success(productService.findById(id));
    }

    @GetMapping
    @NeedPage
    public ServerResponse queryProduct(HttpServletRequest request,ProductParam productParam){
        productParam.setStatus(ProductStatusEnum.ON.getCode());
        SystemRequestHolder.initRequestHolder(request);
        return ServerResponse.success(productService.queryProduct(productParam));
    }
}
