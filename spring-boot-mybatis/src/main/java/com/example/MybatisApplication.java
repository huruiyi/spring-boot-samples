package com.example;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MybatisApplication {

  /*
      https://spring.io/guides/gs/spring-boot/
      https://github.com/spring-guides/gs-spring-boot
  */
  public static void main(String[] args) {
    final ConfigurableApplicationContext ctx = SpringApplication.run(MybatisApplication.class, args);

    System.out.println("# Beans: " + ctx.getBeanDefinitionCount());
    String[] names = ctx.getBeanDefinitionNames();
    Arrays.sort(names);
    //Arrays.asList(names).forEach(System.out::println);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      System.out.println("Let's inspect the beans provided by Spring Boot:");
      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
    };
  }
}

