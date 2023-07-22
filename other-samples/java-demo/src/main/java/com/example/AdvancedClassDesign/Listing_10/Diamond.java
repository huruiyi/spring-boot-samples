package com.example.AdvancedClassDesign.Listing_10;

interface BaseInterface {

  default public void foo() {
    System.out.println("BaseInterface�s foo");
  }
}

class BaseClass {

  public void foo() {
    System.out.println("BaseClass�s foo");
  }
}

public class Diamond extends BaseClass implements BaseInterface {

  public static void main(String[] args) {
    new Diamond().foo();
  }
}
