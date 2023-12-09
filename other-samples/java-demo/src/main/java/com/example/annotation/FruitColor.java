package com.example.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    Color fruitColor() default Color.GREEN;

    public enum Color {
        BULE, RED, GREEN
    }

}
