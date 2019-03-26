package com.example;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;

public class io {
    public static void main(String[] args) {

        // 分支测试
        System.out.println("1111111111111111111111111111111111111111");

        Properties properties = System.getProperties();
        System.out.println(properties.size());
        Enumeration<Object> keys = properties.keys();
        int i = 0;
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = properties.get(key);
            System.out.println(++i + ":" + key + ":" + value);

        }
    }
}
