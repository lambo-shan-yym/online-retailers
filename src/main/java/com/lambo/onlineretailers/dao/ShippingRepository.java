package com.lambo.onlineretailers.dao;

import com.lambo.onlineretailers.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: Category
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:38
 * @Version: 1.0
 */
public interface ShippingRepository extends JpaRepository<Shipping,Integer>{
}
