package com.lambo.onlineretailers.service;

import com.lambo.onlineretailers.dto.ProductDTO;
import com.lambo.onlineretailers.dto.query.ProductParam;
import com.lambo.onlineretailers.entity.Product;

import java.util.List;

/**
 * @ClassName: ICategoryService
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:43
 * @Version: 1.0
 */
public interface IProductService {

    Product save(ProductDTO productDTO);

    Product update(Integer id,ProductDTO productDTO);

    Product findById(Integer id);

    Product deleteById(Integer id);

    List<Product> queryProduct(ProductParam productParam);
}
