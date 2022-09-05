package com.example.AdvancedClassDesign.Listing_13;

interface Function {
    void call();
}

class AnonymousInnerClass {
    public static void main(String[] args) {
        Function function = new Function() {
            public void call() {
                System.out.println("Hello world");
            }
        };
        function.call();
    }
}
