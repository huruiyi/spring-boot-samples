package com.example.JavaClassDesign.QuestionTime_07;

/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809 
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

// Shape.java
class Shape {
    protected void display() {
        System.out.println("Display-base");
    }
}

// Circle.java
class Circle extends Shape {


    public void display() {
        System.out.println("Display-derived");
    }
}
