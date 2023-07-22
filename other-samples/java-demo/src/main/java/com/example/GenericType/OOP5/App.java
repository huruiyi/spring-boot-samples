package com.example.GenericType.OOP5;

public class App {

  public static void main(String[] args) {
    Object[] p = new Point[7];
    p[0] = new Point(4, 55);
    p[1] = new Point(4, 55);
    p[2] = new Point(4, 2);
    p[3] = new Point(12, 55);

    for (int i = 0; i < p.length; i++) {
      if (p[i] instanceof Point) {
        System.out.println(p[i]);
      }
    }
  }
}
