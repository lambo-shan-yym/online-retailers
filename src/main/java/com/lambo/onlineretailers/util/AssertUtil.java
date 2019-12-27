package com.lambo.onlineretailers.util;

import com.lambo.onlineretailers.common.ResponseCode;
import com.lambo.onlineretailers.error.LamboException;
import org.apache.commons.lang3.StringUtils;

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
}
