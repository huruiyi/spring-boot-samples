package com.example.Spring.service.unclassified;

import com.example.Spring.service.HelloWorld;

public class HelloWorldService {

  private HelloWorld helloWorld;

  public HelloWorldService() {

  }

  public HelloWorld getHelloWorld() {
    return this.helloWorld;
  }

  public void setHelloWorld(HelloWorld helloWorld) {
    this.helloWorld = helloWorld;
  }
}