package com.lambo.onlineretailers.dao;

import com.lambo.onlineretailers.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @ClassName: UserRepository
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/24 21:08
 * @Version: 1.0
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(Integer id);

    int countByUsername(String username);

    int countByEmail(String email);

    User findByUsernameAndPassword(String username, String password);

    @Query("select question from User where username = :username")
    String findQuestionByUsername(@Param("username") String username);

    @Query(" select  count(1) from User where username =:username and question=:question and answer=:answer")
    int countByUsernameAndQuestionAndAnswer(User user);

    void updatePasswordByUsername(String newPassoword,String username);

    int countByPasswordAndId(String password,String id);

}
