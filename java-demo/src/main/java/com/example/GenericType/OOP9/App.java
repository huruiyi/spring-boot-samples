package com.example.GenericType.OOP9;

public class App {
    public static void main(String[] args) {
        A outer = new A();

        A.B whatever = outer.new B();

        A.C whatever2 = new A.C();
    }
}
