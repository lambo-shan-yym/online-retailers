package com.lambo.onlineretailers.controller.c;

import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.dto.ShippingDTO;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lambo
 */
@RestController
@RequestMapping
public class ShippingCController {
    @Autowired
    IShippingService shippingService;

    @PostMapping
    public ServerResponse add(@Validated @RequestBody ShippingDTO shippingDTO){
        return ServerResponse.success(shippingDTO);
    }

    @PutMapping(value = "/{id:\\d+}")
    public ServerResponse update(@PathVariable(name = "id") Integer id, User user,
                                     @Validated @RequestBody ShippingDTO shippingDTO){
        return ServerResponse.success(shippingService.update(shippingDTO,id,user.getId()));
    }

    @GetMapping(value = "/{id:\\d+}")
    public ServerResponse getById(@PathVariable(name = "id") Integer id, User user){
        return ServerResponse.success(shippingService.findById(id,user.getId()));
    }


    @DeleteMapping(value = "/{id:\\d+}")
    public ServerResponse delete(@PathVariable(name = "id") Integer id, User user){
        return ServerResponse.success(shippingService.deleteById(id,user.getId()));
    }

}
