package com.example.GenericType.OOPC;

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