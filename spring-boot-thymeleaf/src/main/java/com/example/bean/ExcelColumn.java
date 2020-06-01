package com.example.bean;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /*
     * 列描述
     */
    String desc() default "";

    /**
     * 日期格式
     *
     * @return
     */
    String dateFormat() default "yyyy-MM-dd HH:mm:ss";

    /**
     * 数据精度
     *
     * @return
     */
    int precision() default 2;

    /**
     * 是否是错误列
     *
     * @return
     */
    boolean errorInfoField() default false;
}
