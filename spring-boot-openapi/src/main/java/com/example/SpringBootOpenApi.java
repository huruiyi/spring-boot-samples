package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootOpenApi {

  /**
   * http://localhost:8080/swagger-ui/index.html
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringBootOpenApi.class, args);
  }

}
