package com.lambo.onlineretailers.service.impl;

import com.lambo.onlineretailers.dao.OrderRepository;
import com.lambo.onlineretailers.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:18
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
  
}
