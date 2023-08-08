package com.example.GenericType;

public class GenericType2 {

  public static void main(String[] args) {
    // Box<String> boxOfStrings = new Box<>();
    BoxExt<Employee> boxOfEmployess = new BoxExt<>();
    BoxExt<SuperEmployee> boxOfSuperEmployees = new BoxExt<>();
    // Box<Object> boxOfObjects = new Box<>();
    ProcessingBoxes.processBox(boxOfSuperEmployees);
  }
}


