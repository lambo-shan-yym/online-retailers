package com.lambo.onlineretailers.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lambo.onlineretailers.validator.IsMobile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @ClassName: User
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:07
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserDTO {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Length(min=6,max = 16,message = "密码长度最小为6,最大为16")
    private String password;

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotEmpty(message = "手机不能为空")
    @IsMobile
    private String phone;

    private String question;

    private String answer;

    private Integer role;
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

}
