package com.example.OOP.Demo1;

import java.util.Date;

public class Employee extends Person {
    Employee() {
        this.dateOfEmployment = new Date();
    }

    Employee(Date date) {
        this.dateOfEmployment = date;
    }

    Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    final Date dateOfEmployment;
}
