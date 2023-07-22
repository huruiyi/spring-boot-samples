package com.example.AdvancedClassDesign.QuestionTime_10;

class LambdaFunctionTest {

  public static void main(String[] args) {
    LambdaFunction lambdaFunction = i -> i * i;
    System.out.println(lambdaFunction.apply(10));
  }

  @FunctionalInterface
  interface LambdaFunction {

    int apply(int j);

    boolean equals(Object arg0);
  }
}
