package com.example.JavaClassDesign.Listing_01;

public class Circle extends Shape {

  private int radius;

  public void area() {
    System.out.println("area: " + 3.14 * radius * radius);
  }

  void fillColor() {
    System.out.println("color: " + color);
  }
}
