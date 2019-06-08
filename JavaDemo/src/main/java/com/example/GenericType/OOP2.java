package com.example.GenericType.OOP2;

public class OOP2 {


    public static void main(String[] args) {
        Point p = new Point(10, 20);
        Point p2 = new Point(4, 25);

        System.out.println(p.x);
        System.out.println(p.y);
        System.out.println(p2.x);
        System.out.println(p2.y);
    }


}

class Point {
    Point() {
        System.out.println("This is default constructor");
        x = 100;
        y = 100;
    }

    Point(int first, int second) { // parameters
        x = first;
        y = second;
    }

    int x;
    int y;
}