package com.example.boot_web;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = HelloWorld.class)
public class WebApplication {

  public static void main(String... args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(WebApplication.class, args);
    Arrays.stream(ctx.getBeanDefinitionNames()).forEach(log::info);

    HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
    log.info(helloWorld.sayHi());
    ctx.close();
  }

}
