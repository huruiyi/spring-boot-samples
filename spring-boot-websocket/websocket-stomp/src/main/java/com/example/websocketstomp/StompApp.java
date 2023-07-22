package com.example.websocketstomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class StompApp {

  public static void main(String[] args) {
    SpringApplication.run(StompApp.class, args);
  }

  @Bean
  public TaskExecutor taskExecutor() {
    return new SimpleAsyncTaskExecutor();
  }

}
