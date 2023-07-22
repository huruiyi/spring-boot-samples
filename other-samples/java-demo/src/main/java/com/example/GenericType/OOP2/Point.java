package com.example.GenericType.OOP2;

class Point {

  int x;
  int y;

  Point() {
    System.out.println("This is default constructor");
    x = 100;
    y = 100;
  }
  Point(int first, int second) {
    x = first;
    y = second;
  }
}