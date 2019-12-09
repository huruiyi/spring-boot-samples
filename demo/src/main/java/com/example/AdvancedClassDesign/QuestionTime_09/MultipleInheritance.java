package com.example.AdvancedClassDesign.QuestionTime_09;

interface BaseInterface {
    default void foo() {
        System.out.println("BaseInterface's foo");
    }
}

interface DerivedInterface extends BaseInterface {
    default void foo() {
        System.out.println("DerivedInterface�s foo");
    }
}

interface AnotherInterface {
    public static void foo() {
        System.out.println("AnotherInterface's foo");
    }
}

public class MultipleInheritance implements DerivedInterface, AnotherInterface {
    public static void main(String[] args) {
        new MultipleInheritance().foo();
    }
}
