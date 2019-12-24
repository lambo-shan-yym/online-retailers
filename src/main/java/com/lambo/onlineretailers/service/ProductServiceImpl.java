package com.lambo.onlineretailers.service;

import com.lambo.onlineretailers.dao.ProductRepository;
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
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

}
