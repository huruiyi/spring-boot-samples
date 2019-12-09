package com.example.GenericType.OOP6;

class Point {
    Point() {
        System.out.println("This is default constructor");
        x = 100;
        y = 100;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x;
    int y;
}