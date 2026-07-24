package com.example.routing.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解：标记方法使用哪个数据源
 * 配合 AOP 切面在方法执行前设置 ThreadLocal
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();  // 数据源名称：master / slave
}
