package com.example.GenericType.OOP1;

public class App {

  public static void main(String[] args) {
    Monitor monitor1 = new Monitor();
    monitor1.width = 1000;
    monitor1.height = 200;
    System.out.println(monitor1.width + "  " + monitor1.height);

    Monitor monitor2 = new Monitor();

    monitor2.width = 20000;
    monitor2.getWidth();

    monitor1.getWidth();

    String name = "Arkadiusz";

    System.out.println(name.charAt(4));
  }
}