package com.lambo.onlineretailers.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ProductStatusEnum
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/27 11:40
 * @Version: 1.0
 */
public enum  ProductStatusEnum {
    OFF(1,"未上架"),
    ON(2,"上架"),
    ;
    private int code;
    private String msg;

    ProductStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getRegex(){
        List<Integer> list = new ArrayList<>();
        for (ProductStatusEnum statusEnum : ProductStatusEnum.values()){
            list.add(statusEnum.getCode());
        }
        return StringUtils.join(list, "|");
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
