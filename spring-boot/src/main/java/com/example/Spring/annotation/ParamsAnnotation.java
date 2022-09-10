package com.example.Spring.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ParamsAnnotation {
    String value();
}
