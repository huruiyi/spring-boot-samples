package com.example.Unfiled;

import org.junit.Test;

import java.util.Scanner;

public class Test_Final {
    private static Scanner input;

    static final int b = 123;

    static final int c;

    static {
        c = 123456;
    }

    @Test
    public void Demo0() {
        final int a = 2345;
        System.out.println(a);
//       a = 3456;
//       System.out.println(a);

        System.out.println(b);
//		b = 234;
//		System.out.println(b);
    }

    @Test
    public void Demo1() {
        String a = "hello2";

        final String b = "hello";
        String c = b + 2;
        System.out.println((a == c));

        String d = "hello";
        String e = d + 2;
        System.out.println((a == e));
    }
}
