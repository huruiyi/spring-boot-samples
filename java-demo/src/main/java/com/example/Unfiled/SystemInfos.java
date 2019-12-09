package com.example.Unfiled;

import java.util.Enumeration;
import java.util.Properties;

public class SystemInfos {
    public static void main(String[] args) {

    }

    /**
     * Runtime exec
     */
    static void exec01() {
        Runtime r = Runtime.getRuntime();
        Process p = null;
        try {
            String s = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe D:\\Oauth.txt -T png -o resultxxxxxxxxxxxx.png";
            p = r.exec(s);
        } catch (Exception e) {
            System.out.println("错误:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Properties
     */
    static void exec02() {
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
