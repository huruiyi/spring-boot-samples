package com.example.JavaClassDesign.Listing_01;

class Canvas {

  void getArea() {
    Circle circle = new Circle();
    // call to public method area(), outside package
    circle.area();
  }
}
