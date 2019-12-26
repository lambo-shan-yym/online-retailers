package com.lambo.onlineretailers.dao;

import com.lambo.onlineretailers.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: Category
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:38
 * @Version: 1.0
 */
public interface CategoryRepository extends JpaRepository<Category,Integer>{


    List<Category> findByParentId(Integer parentId);

    Optional<Category> findById(Integer integer);

}
