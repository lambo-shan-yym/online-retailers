package com.lambo.onlineretailers.service.impl;

import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.dao.ProductRepository;
import com.lambo.onlineretailers.dao.SpecificationOperation;
import com.lambo.onlineretailers.dao.specification.SpecificationHelper;
import com.lambo.onlineretailers.dao.specification.StringSpecification;
import com.lambo.onlineretailers.dto.ProductDTO;
import com.lambo.onlineretailers.dto.query.ProductParam;
import com.lambo.onlineretailers.entity.Product;
import com.lambo.onlineretailers.error.LamboException;
import com.lambo.onlineretailers.service.ICategoryService;
import com.lambo.onlineretailers.service.IProductService;
import com.lambo.onlineretailers.util.MyBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ProductServiceImpl
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/27 10:52
 * @Version: 1.0
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ICategoryService categoryService;

    @Override
    public Product save(ProductDTO productDTO) {
        Product product = new Product();
        categoryService.checkCategory(productDTO.getCategoryId());
        BeanUtils.copyProperties(productDTO, product);
        return productRepository.save(product);
    }

    @Override
    public Product update(Integer id, ProductDTO productDTO) {
        Product product = checkById(id);
        BeanUtils.copyProperties(productDTO, product, MyBeanUtils.getNullPropertyNames(productDTO));
        return productRepository.save(product);
    }

    @Override
    public Product findById(Integer id) {
        Product product = checkById(id);
        return product;
    }

    @Override
    public Product deleteById(Integer id) {
        Product product = checkById(id);
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public List<Product> queryProduct(ProductParam productParam) {
      /*  Specification<Product> specification = new Specification<Product>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(productParam.getName())) {
                    predicates.add(cb.like(root.get("name"), productParam.getName() + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };*/
        List<Specification<Product>> list = new ArrayList<>();
        if (StringUtils.isNotBlank(productParam.getName())) {
            list.add(new StringSpecification("name",
                    SpecificationOperation.LOGICAL_OPERATOR_START_WITH, productParam.getName()));
        }
        return productRepository.findAll(SpecificationHelper.and(list));
    }

    private Product checkById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new LamboException(ResponseCode.PRODUCT_NOT_EXIST));
        return product;
    }
}
