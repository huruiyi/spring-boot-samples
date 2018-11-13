package com.example;

public class StringDemo {
    public static void main(String[] args) {
        String name = "盖伦";
        int kill = 8;
        String title = "超神";
        String sentence = name + " 在进行了连续  " + kill + " 次击杀后，获得了 " + title + " 的称号 ";
        //直接使用+进行字符串连接，编码感觉会比较繁琐，并且维护性差，易读性差
        System.out.println(sentence);
        String sentenceFormat = "%s 在进行了连续 %d 次击杀后， 获得了 %s 的称号%n";
        //格式化输出，%s表示字符串，%d表示数字，%n表示换号
        System.out.printf(sentenceFormat, name, kill, title);
        System.out.format(sentenceFormat, name, kill, title);
        //format和printf能够达到一模一样的效果

        int year = 2020;
        System.out.format("%d%n", year);

        // 用%n或\n没差
        // 直接打印数字
        System.out.printf("%8d%n", year);

        // 用printf还是format没差
        // 总长度为8，默认右对齐
        System.out.printf("%-8d%n", year);

        // 总长度为8，默认左对齐
        System.out.printf("%08d%n", year);

        // 总长度为8，不够补0
        System.out.printf("%,8d%n", year * 10000);

        // 千位分隔符
        System.out.format("%.2f%n", Math.PI);

    }
}
