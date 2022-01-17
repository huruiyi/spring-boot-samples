package com.example.demo.utils;

import java.util.UUID;

public class MongoUtils {

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
