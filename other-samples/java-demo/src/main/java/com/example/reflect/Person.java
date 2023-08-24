package com.example.reflect;

public class Person {

  public String name;
  @SuppressWarnings("unused")
  private int age;

  public Person() {

  }

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public static void say(String str) {
    System.out.println("我在说： " + str);
  }

  public void print(int i) {
    System.out.println("我在写数字： " + i);
  }
}
