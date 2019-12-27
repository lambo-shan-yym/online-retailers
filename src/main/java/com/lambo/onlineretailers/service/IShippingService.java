package com.lambo.onlineretailers.service;

import com.lambo.onlineretailers.dto.ShippingDTO;
import com.lambo.onlineretailers.entity.Shipping;

import java.util.List;

/**
 * @ClassName: ICategoryService
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:43
 * @Version: 1.0
 */
public interface IShippingService {

    public Shipping add(ShippingDTO shippingDTO, Integer userId);

    public Shipping update(ShippingDTO shippingDTO,Integer id,Integer userId);

    public Shipping findById(Integer id, Integer userId) ;

    public Shipping deleteById(Integer id, Integer userId);

    public List<Shipping> findList();
}
