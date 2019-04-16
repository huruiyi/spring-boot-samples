package com.example;

public class Sys {
    public static void main(String[] args) {
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
}