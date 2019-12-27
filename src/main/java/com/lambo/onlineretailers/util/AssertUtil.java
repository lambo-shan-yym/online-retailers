package com.lambo.onlineretailers.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.enums.BooleanTypeEnum;
import com.lambo.onlineretailers.error.LamboException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: AssertUtil
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/25 11:04
 * @Version: 1.0
 */
public class AssertUtil {
    /**
     * 断言 为空
     * @param str
     * @param message
     * @author yym
     */
    public static void isEmpty(String str, String message) {
        if(StringUtils.isBlank(str)){
            throw new LamboException(ResponseCode.REQUIRE_ARGUMENT,message);
        }
    }

    /**
     * 断言 正则表达式匹配
     * @param str
     * @param message
     * @author yym
     */
    public static void isMatches(String str, String regex, String message) {
        if (!str.matches(regex)) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }


    /**
     * 断言 布尔值正常
     * @param expression
     * @param message
     * @author yym
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }

    /**
     * 断言 字符串是否是Boolean值
     * @param str
     * @param message
     * @author yym
     */
    public static void isBoolean(String str, String message) {
        if (!str.equals(BooleanTypeEnum.TRUE.getKey()) && !str.equals(BooleanTypeEnum.FALSE.getKey())) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }
    

    /**
     * 断言 字符数组长度
     * @param str
     * @param message
     * @param len
     * @author yym
     */
    public static void isLongWithLength(String str, int len, String message) {
        String[] strings = str.split("\\,");
        if (len != strings.length) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
        for (String s : strings) {
            isLong(s, message);
        }
    }

    /**
     * 断言 字符数组最大长度
     * @param str
     * @param message
     * @param len
     * @author yym
     */
    public static void isIntegerWithMaxLength(String str, int len, String message) {
        String[] strings = str.split("\\,");
        if (strings.length > len) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
        for (String s : strings) {
            isInteger(s, message);
        }
    }
    

    /**
     * 断言 若值不空的情况下，正则表达式不匹配
     * @param str
     * @param message
     * @author yym
     */
    public static void isNotMatches4NotEmpty(String str, String regex, String message) {
        if (StringUtils.isNotBlank(str)) {
            if (!str.matches(regex)) {
                throw new LamboException(ResponseCode.INVALID_ARGUMENT,  message );
            }
        }
    }

    /**
     * 断言 Json字符串
     * @param json
     * @param message
     * @author yym
     */
    public static void isJsonStr(String json, String message) {
        boolean state = false;
        try {
            JSONObject.parse(json);
            state = true;
        } catch (Exception e) {
            state = false;
        }

        if (!state) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  字符串长度范围
     * @param value
     * @param min
     * @param max
     * @param message
     * @author yym
     */
    public static void rangeLength(String value, int min, int max, String message) {
        if (value.length() < min || value.length() > max) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT,  message );
        }
    }

    /**
     * 断言  字符串长度范围
     * @param value
     * @param min
     * @param max
     * @param errorCode
     * @author yym
     */
    public static void rangeLength(String value, int min, int max, ResponseCode errorCode) {
        if (value.length() < min || value.length() > max) {
            throw new LamboException(errorCode);
        }
    }

    /**
     * 最大长度验证
     * @param value
     * @param length
     * @param message
     * @author yym
     */
    public static void maxLength(String value, int length, String message) {
        if (value.length() > length) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }

    /**
     * 等于长度验证
     * @param value
     * @param length
     * @param message
     * @author yym
     */
    public static void equalLength(String value, int length, String message) {
        if (value.length() != length) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }

    /**
     * 断言  数字
     * @param str
     * @param message
     * @author yym
     */
    public static void isNumber(String str, String message) {
        if (!str.matches("^[0-9]*$")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT,  message );
        }
    }

    /**
     * 断言  不包含数字
     * @param str
     * @param message
     * @author yym
     */
    public static void isNotNumber(String str, String message) {
        if (str.matches("^[0-9]*$")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  数字主键Integer
     * @param str
     * @param message
     * @author yym
     */
    public static void isInteger(String str, String message) {
        isNumber(str, message);
        try {
            Integer.valueOf(str);
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  大于零判断
     * @param str
     * @param message
     * @author yym
     */
    public static void moreThanZero(String str, String message) {
        isNumber(str, message);
        try {
            if (Integer.valueOf(str) <= 0) {
                throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
            }
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }

    /**
     * 断言  数字主键Long
     * @param str
     * @param message
     * @author yym
     */
    public static void isLong(String str, String message) {
        isNumber(str, message);
        try {
            Long.valueOf(str);
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  数字主键Float
     * @param str
     * @param message
     * @author yym
     */
    public static void isFloat(String str, String message) {
        try {
            Float.valueOf(str);
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }

    /**
     * 断言  数字为10以内
     * @param str
     * @return boolean
     * @author yym
     */
    public static void isNumberWith10(String str, String message) {
        if (!str.matches("^([1-9]|10)$")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }

    /**
     * 断言  字符不包含中文
     * @param str
     * @return boolean
     * @author yym
     */
    public static void isChinese(String str, String message) {
        if (str.matches(".*[\\u4e00-\\u9faf].*")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }

    /**
     * 断言  数字为2以内
     * @param str
     * @return boolean
     * @author yym
     */
    public static void isNumberWith2(String str, String message) {
        if (!str.matches("^[1-2]$")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message );
        }
    }

    /**
     * 断言  数字为2以内
     * @param str
     * @return boolean
     * @author yym
     */
    public static void isNumberWith1(String str, String message) {
        if (!str.matches("^[1]$")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT,  message );
        }
    }

    /**
     * 断言  数字为5以内
     * @param str
     * @return boolean
     * @author yym
     */
    public static void isNumberWith5(String str, String message) {
        if (!str.matches("^[1-5]$")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT,  message );
        }
    }

    /**
     * 断言  数字 和 逗号
     * @param str
     * @return boolean
     * @author yym
     */
    public static void isNumberAndComma(String str, String message) {
        // if (!str.matches("^[0-9|,]*$"))
        if (!str.matches("\\d+(,\\d+)*")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT,  message );
        }
    }

    /**
     * 断言  数字范围
     * @param value
     * @param min
     * @param max
     * @param message
     * @author yym
     */
    public static void range(String value, int min, int max, String message) {
        isNumber(value, message);

        int num = 0;
        try {
            num = Integer.valueOf(value);
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }

        if (num < min || num > max) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  数字范围      
     * @param value
     * @param min
     * @param max
     * @param message
     * @author yym
     */
    public static void range4Int(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  数字范围      
     * @param value
     * @param min
     * @param max
     * @param message
     * @author yym
     */
    public static void range4Long(long value, long min, long max, String message) {
        if (value < min || value > max) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  最小值
     * @param value
     * @param numVal
     * @param message
     * @author yym
     */
    public static void min(String value, int numVal, String message) {
        isNumber(value, message);
        int num = Integer.valueOf(value);
        if (num < numVal) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  最大值
     * @param value
     * @param numVal
     * @param message
     * @author yym
     */
    public static void max(String value, int numVal, String message) {
        isNumber(value, message);
        int num = Integer.valueOf(value);
        if (num > numVal) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 等于长度验证
     * @param value
     * @param numVal
     * @param message
     * @author yym
     */
    public static void equal(String value, int numVal, String message) {
        isNumber(value, message);
        int num = Integer.valueOf(value);
        if (num != numVal) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 对象是否null	  
     * @param obj
     * @param message
     * @author yym
     */
    public static void isObjectNull(Object obj, String message) {
        if (obj == null) {
            throw new LamboException(ResponseCode.REQUIRE_ARGUMENT, message);
        }
    }

    /**
     * 断言 若值不空的情况下，正则表达式不匹配
     * @param value
     * @param message
     * @author yym
     */
    public static void isNotMatches4RangeLength(String value, int min, int max, String message) {
        if (value != null) {
            rangeLength(value, min, max, message);
        }
    }

    /**
     * 断言 是否合法的数字串      
     * @param value
     * @param message
     * @author yym
     */
    public static void isValidIds(String value, String message) {
        String[] arrStr = StringUtils.split(value, ",");
        for (String str : arrStr) {
            isNumber(str, message);
        }
    }

    /**
     * 断言 是否合法的数字串      
     * @param value
     * @param message
     * @author yym
     */
    public static void isValidIds4Int(String value, String message) {
        String[] arrStr = StringUtils.split(value, ",");
        for (String str : arrStr) {
            isInteger(str, message);
        }
    }

    /**
     * 断言 是否合法的数字串      
     * @param value
     * @param message
     * @author yym
     */
    public static void isValidIds4Long(String value, String message) {
        String[] arrStr = StringUtils.split(value, ",");
        for (String str : arrStr) {
            isLong(str, message);
        }
    }

    /**
     * 断言 是否合法的字符串      
     * @param value
     * @param min
     @param max
      * @param message
     * @author yym
     */
    public static void isValidStrs(String value, int min, int max, String message) {
        String[] arrStr = StringUtils.split(value, ",");
        for (String str : arrStr) {
            isEmpty(str, message);
            isChinese(str, message);
            rangeLength(str, min, max, message);
        }
    }

    /**
     * 断言 不能是纯数字的字符串
     * @param value
     * @param min
     * @param max
     @param message
      * @author yym
     */
    public static void isValidStr(String value, int min, int max, String message) {
        isNotNumber(value, message);
        rangeLength(value, min, max, message);
    }

    /**
     * 断言 电话号码
     *
     * @param str
     * @param message
     * @author yym
     */
    public static void isMobile(String str, String message) {
        if (!str.matches("^1[345789]\\d{9}$")) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言  URL
     *
     * @param str
     * @return boolean
     * @author yym
     */
    public static void isURL(String str, String message) {
        Pattern pattern = Pattern
                .compile("\\b(([\\w-]+://?|www[.])[^\\s()<>]+(?:\\([\\w\\d]+\\)|([^[:punct:]\\s]|/)))");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言并返回数字Long
     * @param str
     * @param message
     * @return long
     * @author yym
     */
    public static long checkLong(String str, String message) {
        isNumber(str, message);
        try {
            return Long.valueOf(str);
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言是否合法的数字串
     * @param value
     * @param message
     * @return 分割的long列表
     * @author yym
     */
    public static List<Long> checkValidIds4Long(String value, String message) {
        String[] arrStr = StringUtils.split(value, ",");
        List<Long> longList = new ArrayList<Long>(arrStr.length);
        for (String str : arrStr) {
            longList.add(checkLong(str, message));
        }
        return longList;
    }

    /**
     * 断言 Json字符串
     * @param json
     * @param message
     * @return JSONObject
     * @author yym
     */
    public static JSONObject isJsonObject(String json, String message) {
        try {
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言 Json字符串
     * @param json
     * @param message
     * @return JSONArray
     * @author yym
     */
    public static JSONArray checkJsonArray(String json, String message) {
        try {
            return JSONObject.parseArray(json);
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }

    /**
     * 断言 Json字符串
     * @param json
     * @param clazz
     * @param message
     * @return List<T>
     * @author yym
     */
    public static <T> List<T> checkJsonArray(String json, Class<T> clazz, String message) {
        try {
            return JSONObject.parseArray(json, clazz);
        } catch (Exception e) {
            throw new LamboException(ResponseCode.INVALID_ARGUMENT, message);
        }
    }
}
