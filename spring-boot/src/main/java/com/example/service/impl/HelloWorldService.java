package com.example.service.impl;

import com.example.service.SimpleService;

public class HelloWorldService {

  private SimpleService helloWorld;

  public HelloWorldService() {

  }

  public SimpleService getHelloWorld() {
    return this.helloWorld;
  }

  public void setHelloWorld(SimpleService helloWorld) {
    this.helloWorld = helloWorld;
  }
}
