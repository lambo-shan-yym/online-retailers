package com.lambo.onlineretailers.util;

import java.util.UUID;

/**
 * @ClassName: UUIDUtil
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/25 13:09
 * @Version: 1.0
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
