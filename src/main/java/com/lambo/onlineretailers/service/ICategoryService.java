package com.lambo.onlineretailers.service;

import com.lambo.onlineretailers.dto.CategoryDTO;
import com.lambo.onlineretailers.entity.Category;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: ICategoryService
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:43
 * @Version: 1.0
 */
public interface ICategoryService {

    Category save(CategoryDTO category);

    Category update(Integer id,CategoryDTO category);

    Category deleteById(Integer id);

    List<Category> findByParentId(Integer parentId);

    Category findById(Integer id);

    List<Integer> selectCategoryAndChildById(Integer categoryId);

}
