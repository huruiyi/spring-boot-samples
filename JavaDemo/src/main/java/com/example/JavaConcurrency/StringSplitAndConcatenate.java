package com.example.JavaConcurrency;

import java.util.Arrays;

/**

 * 作者：胡睿毅
 * 文件名：StringSplitAndConcatenate
 * 日期：2019/5/26 15:00
 **/

class StringConcatenator {
    public static String result = "";

    public static void concatStr(String str) {
        result = result + " " + str;
    }
}

class StringSplitAndConcatenate {
    public static void main(String[] args) {
        String words[] = "the quick brown fox jumps over the lazy dog".split(" ");
        Arrays.stream(words).forEach(StringConcatenator::concatStr);
        System.out.println(StringConcatenator.result);
    }
}
