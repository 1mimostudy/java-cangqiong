package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: AutoFill
 * Package: com.sky.annotation
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/1/26 13:38
 * @Version 1.0
 */
/*
*
* */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
        //数据库操作类型
        OperationType value();

}
