package com.lambo.onlineretailers.redis;

import com.lambo.onlineretailers.dto.UserDTO;
import com.lambo.onlineretailers.service.IUserService;
import com.lambo.onlineretailers.util.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lambo
 */
@SpringBootTest
class RedisServiceTest {

    @Autowired
    RedisService redisService;

    @Autowired
    IUserService userService;
    @Test
    public void testRedis(){
        String token = UUIDUtil.uuid();
        UserDTO userDTO = userService.findByUsername("lambo");
        redisService.set(UserKey.TOKEN,token,userDTO);
    }
}