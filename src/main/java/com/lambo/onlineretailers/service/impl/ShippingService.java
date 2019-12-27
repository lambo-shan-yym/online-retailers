package com.lambo.onlineretailers.service.impl;

import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.dao.ShippingRepository;
import com.lambo.onlineretailers.dto.ShippingDTO;
import com.lambo.onlineretailers.entity.Shipping;
import com.lambo.onlineretailers.error.LamboException;
import com.lambo.onlineretailers.service.IShippingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lambo
 */
@Service
public class ShippingService implements IShippingService {
    @Autowired
    ShippingRepository shippingRepository;

    public Shipping add(ShippingDTO shippingDTO, Integer userId) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(shippingDTO, shipping);
        shipping.setUserId(userId);
        return shippingRepository.save(shipping);
    }

    public Shipping update(ShippingDTO shippingDTO,Integer id,Integer userId){
        Shipping shipping = checkById(id,userId);
        BeanUtils.copyProperties(shippingDTO,shipping);
        return shippingRepository.save(shipping);
    }

    public Shipping findById(Integer id, Integer userId) {
        return checkById(id,userId);

    }

    public Shipping deleteById(Integer id, Integer userId){
        Shipping shipping =checkById(id,userId);
        shippingRepository.deleteById(id);
        return shipping;

    }

    @Override
    public List<Shipping> findList() {
        return shippingRepository.findAll();
    }


    private Shipping checkById(Integer id,Integer userId) {
        Shipping shipping = shippingRepository.findById(id).orElseThrow(() ->
                new LamboException(ResponseCode.SHIPPING_NOT_EXIST));

        if(userId!=null&&userId!=shipping.getUserId()){
            throw new LamboException(ResponseCode.SHIPPING_NOT_EXIST);
        }
        return shipping;

    }

}
