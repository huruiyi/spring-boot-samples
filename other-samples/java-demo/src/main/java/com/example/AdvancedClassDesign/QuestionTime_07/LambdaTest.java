package com.example.AdvancedClassDesign.QuestionTime_07;

interface DoNothing {

  default void doNothing() {
    System.out.println("doNothing");
  }
}

@FunctionalInterface
interface DontDoAnything extends DoNothing {

  @Override
  abstract void doNothing();
}

class LambdaTest {

  public static void main(String[] args) {
    DontDoAnything beIdle = () -> System.out.println("be idle");
    beIdle.doNothing();
  }
}
