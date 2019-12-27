package com.lambo.onlineretailers.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: NeedPage
 * @Author: yym
 * @Description: 需要获取分页信息的注解
 * 是为了防止在分页的接口中不小心漏了SystemRequestHolder.initRequestHolder(request);
 * 进而导致SystemRequestHolder.getPageable()获取到的对象为空
 * @Date: 2019/12/27 15:37
 * @Version: 1.0
 */
@Documented //文档生成时，该注解将被包含在javadoc中，可去掉
@Target(ElementType.METHOD)//目标是方法
@Retention(RetentionPolicy.RUNTIME) //注解会在class中存在，运行时可通过反射获取
@Inherited
public @interface NeedPage {
    boolean request() default true;
}
