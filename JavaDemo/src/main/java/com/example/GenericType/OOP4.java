package com.example.GenericType.OOP4;

public class OOP4 {
    public static void main(String[] args) {
        nameOfInterface a = new Employee(1000);
        ((Employee) a).getSalary();

        nameOfInterface b = new Programmer(2000);
        ((Employee) b).getSalary();

    }

}

interface nameOfInterface {
    double PI = 3.14;

    void sample();
}

interface someOtherInterface {

}

class Employee implements nameOfInterface, someOtherInterface {
    public Employee(double salary) {
        this.salary = salary;
    }

    @Override
    public void sample() {

    }

    private double salary;

    double getSalary() {
        System.out.println(this.salary);
        return this.salary;
    }
}

class A {

}

class Programmer extends Employee {
    public Programmer(double salary) {
        super(salary);
    }
}