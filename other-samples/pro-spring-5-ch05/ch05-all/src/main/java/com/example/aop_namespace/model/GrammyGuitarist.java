package com.example.aop_namespace.model;

public class GrammyGuitarist {

  public void sing() {
    System.out.println("sing: Gravity is working against me And gravity wants to bring me down");
  }

  public void sing(Guitar guitar) {
    System.out.println("play: " + guitar.play());
  }

  public void talk() {
    System.out.println("talk");
  }

}
