package com.example.component;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldComponent {

  @PostConstruct
  public void sayHello() {
    System.out.println("Hello World, from Spring Boot 2!");
  }

}
