package com.example.GenericType.OOP4.Override2;


class Employee extends Person {
    double salary;

    public Employee(String name, String surname, double salary) {
        super(name, surname);
        this.salary = salary;
    }

    @Override
    void getDescription() {
        System.out.println("I'm employee");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Salary: " + salary);
    }
}