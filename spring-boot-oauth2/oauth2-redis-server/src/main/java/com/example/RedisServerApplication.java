package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisServerApplication {

  /**
   * get access_token:
   * client_1:
   * http://localhost:20003/oauth/token?username=user_1&password=123456&grant_type=client_credentials&scope=select&client_id=client_1&client_secret=123456
   * client_2:
   * http://localhost:20003/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=client_2&client_secret=123456
   * <p>
   * http://localhost:20003/hello
   * <p>
   * use access_token:
   * http://localhost:20003/api/profile?access_token=obs-QIO21wezfsNWi1rywpbo4lw
   */
  public static void main(String[] args) {
    SpringApplication.run(RedisServerApplication.class, args);
  }

}
