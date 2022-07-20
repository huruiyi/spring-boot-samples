package com.example.Unfiled;

public class Random_Number {

    public static void main(String[] args) {
        System.out.println(Character.MAX_RADIX);
        Test1();
    }

    static void Test1() {
        Integer max36 = Integer.valueOf("ZZZZZ", 36);
        System.out.println(max36);    //36进制最大值
        String item = Integer.toUnsignedString(max36, 36);
        System.out.println(item);
    }

    static void Test2() {
        for (int i = 0; i <= 60466175; i++) {
            String item = Integer.toUnsignedString(i, 36);
            System.out.println(item + "-" + Integer.valueOf(item, 36));
        }
    }
}
