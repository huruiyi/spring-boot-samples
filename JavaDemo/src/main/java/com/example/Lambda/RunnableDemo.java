package com.example.Lambda;

public class RunnableDemo {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            System.out.println("Hello World, Hello Runnable");
        };

        runnable.run();
    }
}
