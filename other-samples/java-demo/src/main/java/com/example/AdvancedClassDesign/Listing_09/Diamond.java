package com.example.AdvancedClassDesign.Listing_09;

interface Interface1 {
    default public void foo() {
        System.out.println("Interface1�s foo");
    }
}

interface Interface2 {
    default public void foo() {
        System.out.println("Interface2�s foo");
    }
}

public class Diamond implements Interface1 /*,Interface1*/ {
    public static void main(String[] args) {
        new Diamond().foo();
    }
}
