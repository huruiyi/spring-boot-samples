package com.example.AdvancedClassDesign.Listing_08;

// Rollable interface can be implemented by circular shapes such as Circle
interface Rollable {

  void roll(float degree);
}

// Shape is the base class for all shape objects; shape objects that are associated with
// a parent shape object is remembered in the parentShape field
abstract class Shape {

  private Shape parentShape;

  abstract double area();

  public Shape getParentShape() {
    return parentShape;
  }

  public void setParentShape(Shape shape) {
    parentShape = shape;
  }
}

abstract class CircularShape extends Shape implements Rollable {

}

// Circle is a concrete class that is-a subtype of CircularShape; 
// you can roll it and hence implements Rollable through CircularShape base class 
public class Circle extends CircularShape {

  private int xPos, yPos, radius;

  public Circle(int x, int y, int r) {
    xPos = x;
    yPos = y;
    radius = r;
  }

  public static void main(String[] s) {
    Circle circle = new Circle(10, 10, 20);
    circle.roll(45);
  }

  public double area() {
    return Math.PI * radius * radius;
  }

  @Override
  public void roll(float degree) {
    // implement rolling functionality here...
    // for now, just print the rolling degree to console
    System.out.printf("rolling circle by %f degrees", degree);
  }
}
