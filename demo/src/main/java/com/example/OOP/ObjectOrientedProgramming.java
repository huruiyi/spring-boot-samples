package com.example.OOP;

public class ObjectOrientedProgramming {

    public static void main(String[] args) {
        Test a = new Test();

        a.printSomethingOut("Arek", "Włodarczyk");
        a.printSomethingOut("Viola", "Włodarczyk");
        a.printSomethingOut("Viola", "Włodarczyk");
        a.printSomethingOut("Viola", "Włodarczyk");
        a.printSomethingOut("Viola", "Włodarczyk");
        a.printSomethingOut("Viola", "Włodarczyk");

        int result = a.add(55, 15);
        System.out.println(result);
    }
}

class Test {
    void printSomethingOut(String name, String surname) {
        System.out.println("Name: " + name);
        System.out.println("Surname " + surname);
    }

    int add(int x, int y) {
        return x + y;
    }

    double add(double x, double y) {
        return x + y;
    }

    double divide(double x, double y) {
        if (y == 0)
            return 0;

        System.out.println("test");
        return x / y;
    }

}
