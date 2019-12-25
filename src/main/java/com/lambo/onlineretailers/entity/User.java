package com.lambo.onlineretailers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: User
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:07
 * @Version: 1.0
 */
@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    @Column(name = "register_time")
    private Date registerTime;
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    private Integer role;

    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

}
