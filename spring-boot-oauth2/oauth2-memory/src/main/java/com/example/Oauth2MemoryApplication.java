package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oauth2MemoryApplication {

  /**
   * <p>
   * <a
   * href="http://localhost:20002/oauth/token?username=user_1&password=user_1_pass&grant_type=password&scope=select&client_id=client_2&client_secret=client_2_pass">Token获取</a>
   * <p>
   * <a href="http://localhost:20002/order/1?access_token=DxqOpr2AGUnwqaEPAS1r5PyJlPA">订单资源</a>
   */
  public static void main(String[] args) {
    SpringApplication.run(Oauth2MemoryApplication.class, args);
  }

}
