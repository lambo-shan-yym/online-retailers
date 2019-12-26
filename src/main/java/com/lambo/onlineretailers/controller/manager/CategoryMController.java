package com.lambo.onlineretailers.controller.manager;

import com.lambo.onlineretailers.common.ServerResponse;
import com.lambo.onlineretailers.dto.CategoryDTO;
import com.lambo.onlineretailers.entity.User;
import com.lambo.onlineretailers.page.SystemRequestHolder;
import com.lambo.onlineretailers.service.ICategoryService;
import com.lambo.onlineretailers.validator.InsertValidator;
import com.lambo.onlineretailers.validator.UpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: CategoryMController
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/26 11:11
 * @Version: 1.0
 */
@RestController
@RequestMapping("/v0.1/m/category")
public class CategoryMController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping(value = "")
    public ServerResponse save(@Validated(value = InsertValidator.class) @RequestBody CategoryDTO categoryDTO, User user) {
        return ServerResponse.success(categoryService.save(categoryDTO));
    }

    @PutMapping("/{id:\\d+}")
    public ServerResponse update(@PathVariable(name = "id") Integer id,
                                 @Validated(value = UpdateValidator.class) @RequestBody CategoryDTO categoryDTO, User user) {
        return ServerResponse.success(categoryService.update(id, categoryDTO));
    }

    @DeleteMapping("/{id:\\d+}")
    public ServerResponse deleteById(@PathVariable(name = "id") Integer id, User user) {
        return ServerResponse.success(categoryService.deleteById(id));
    }

    @GetMapping("/{id:\\d+}")
    public ServerResponse findById(@PathVariable(name = "id") Integer id, User user) {
        return ServerResponse.success(categoryService.findById(id));
    }

    @GetMapping
    public ServerResponse getList(HttpServletRequest request) {
        SystemRequestHolder.initRequestHolder(request);
        return ServerResponse.success(SystemRequestHolder.getPageable());
    }

    @GetMapping("/get_parallel_category")
    public ServerResponse getChildParallelCategory(@RequestParam(value = "parent_id",defaultValue = "0") Integer parentId){
        return ServerResponse.success(categoryService.findByParentId(parentId));
    }
    @GetMapping("/get_deep_category")
    public ServerResponse getCategoryAndDeepChildCategory(@RequestParam(value = "category_id",defaultValue = "0") Integer categoryId){
        return ServerResponse.success(categoryService.selectCategoryAndChildById(categoryId));
    }


}
