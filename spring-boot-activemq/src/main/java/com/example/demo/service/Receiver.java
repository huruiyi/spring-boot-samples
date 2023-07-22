package com.example.demo.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  @JmsListener(destination = "activemq-listener")
  public void receiveMessage(String message) {
    System.out.println("接受到: <" + message + ">");
  }
}
