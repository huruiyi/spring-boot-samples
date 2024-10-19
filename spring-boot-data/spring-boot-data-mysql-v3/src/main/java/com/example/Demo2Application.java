package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Demo2Application {


  /**
   * <a href="https://github.com/Ikhiloya">Ikhiloya</a>
   * 虚拟机选项： -Dspring.profiles.active=mysql
   */
  public static void main(String[] args) {
    SpringApplication.run(Demo2Application.class, args);
  }

}
