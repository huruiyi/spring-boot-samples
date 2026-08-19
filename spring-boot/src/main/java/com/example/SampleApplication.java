package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class SampleApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SampleApplication.class, args);
    log.info("# Beans: {}", context.getBeanDefinitionCount());
    String[] names = context.getBeanDefinitionNames();
    Arrays.sort(names);
    Arrays.asList(names).forEach(log::info);
  }


  // nginx.conf redis-session测试
  // java -jar demo1.jar --server.port=8012
  // java -jar demo2.jar --server.port=8013
  // java -jar app.jar --spring.application.name=spring-boot
  // export SPRING_APPLICATION_NAME=spring-boot
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SampleApplication.class);
  }

}
