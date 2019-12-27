package com.lambo.onlineretailers.controller.manager;

import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.dto.ProductDTO;
import com.lambo.onlineretailers.dto.query.ProductParam;
import com.lambo.onlineretailers.service.IProductService;
import com.lambo.onlineretailers.validator.InsertValidator;
import com.lambo.onlineretailers.validator.UpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ProductMController
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/27 10:56
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/v0.1/m/product")
public class ProductMController {
    @Autowired
    IProductService productService;

    @PostMapping(value = "")
    public ServerResponse add(@Validated(value = InsertValidator.class) @RequestBody ProductDTO productDTO){
        return ServerResponse.success(productService.save(productDTO));
    }

    @PutMapping(value = "/{id:\\d+}")
    public ServerResponse update(@Validated(value = UpdateValidator.class)@RequestBody ProductDTO productDTO,
                                 @PathVariable(name = "id") Integer id){
        return ServerResponse.success(productService.update(id,productDTO));
    }

    @GetMapping(value = "/{id:\\d+}")
    public ServerResponse get(@PathVariable(name = "id") Integer id){
        return ServerResponse.success(productService.findById(id));
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public ServerResponse delete(@PathVariable(name = "id") Integer id){
        return ServerResponse.success(productService.deleteById(id));
    }

    @GetMapping
    public ServerResponse queryProduct(ProductParam productParam){
        return ServerResponse.success(productService.queryProduct(productParam));
    }
}
