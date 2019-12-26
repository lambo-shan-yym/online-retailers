package com.lambo.onlineretailers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lambo.onlineretailers.validator.InsertValidator;
import com.lambo.onlineretailers.validator.UpdateValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: Category
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 22:00
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;

    @NotNull(message = "父节点id不能为空,一级节点可设置为0",groups = {InsertValidator.class})
    @JsonProperty(value = "parent_id")
    private Integer parentId;
    @NotBlank(message = "节点名称不能为空",groups = {InsertValidator.class})
    private String name;

    private String status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;
}
