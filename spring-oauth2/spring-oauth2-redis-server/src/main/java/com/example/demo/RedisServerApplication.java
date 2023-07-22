package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisServerApplication {

  /**
   * http://localhost:8080/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=client_2&client_secret=123456
   * http://localhost:8080/hello http://localhost:8080/api/profile?access_token=obs-QIO21wezfsNWi1rywpbo4lw
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(RedisServerApplication.class, args);
  }

}
