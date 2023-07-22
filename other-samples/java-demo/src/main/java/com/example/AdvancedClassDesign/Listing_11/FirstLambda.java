package com.example.AdvancedClassDesign.Listing_11;

interface LambdaFunction {

  void call();
}

class FirstLambda {

  public static void main(String[] args) {
    LambdaFunction lambdaFunction = () -> System.out.println("Hello world");
    lambdaFunction.call();
  }
}
