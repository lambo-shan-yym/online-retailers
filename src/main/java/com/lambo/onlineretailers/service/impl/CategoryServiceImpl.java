package com.lambo.onlineretailers.service.impl;

import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.dao.CategoryRepository;
import com.lambo.onlineretailers.dto.CategoryDTO;
import com.lambo.onlineretailers.entity.Category;
import com.lambo.onlineretailers.error.LamboException;
import com.lambo.onlineretailers.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ICategoryService
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/26 10:48
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Integer id,CategoryDTO categoryDTO) {
        checkCategory(id);
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteById(Integer id) {
        Category category = checkCategory(id);
        categoryRepository.deleteById(id);
        return category;
    }

    @Override
    public List<Category> findByParentId(Integer parentId) {
        return categoryRepository.findByParentId(parentId);
    }

    @Override
    public Category findById(Integer id) {
        return checkCategory(id);
    }

    @Override
    public List<Integer> selectCategoryAndChildById(Integer categoryId) {
        if(categoryId==null){
            return Collections.EMPTY_LIST;
        }
        Set<Category> categorySet = findChildCategory(new HashSet<>(), categoryId);

        List<Integer> categoryList = new ArrayList<>();
        categorySet.forEach(item->categoryList.add(item.getId()));
        return categoryList;
    }


    public Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId){
        Category category =categoryRepository.getOne(categoryId);
        if(category!=null){
            categorySet.add(category);
        }
        List<Category> categoryList = categoryRepository.findByParentId(category.getId());
        categoryList.forEach(item->findChildCategory(categorySet,item.getId()));
        return categorySet;
    }
    public Category checkCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new LamboException(ResponseCode.CATEGORY_NOT_EXIST));
        return category;
    }
}
