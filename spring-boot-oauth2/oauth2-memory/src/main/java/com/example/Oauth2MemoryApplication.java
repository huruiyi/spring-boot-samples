package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oauth2MemoryApplication {

  /**
   * http://localhost:10300/oauth/token?username=user_1&password=user_1_pass&grant_type=password&scope=select&client_id=client_2&client_secret=client_2_pass
   * <p>
   * http://localhost:10300/order/1?access_token=DxqOpr2AGUnwqaEPAS1r5PyJlPA
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(Oauth2MemoryApplication.class, args);
  }

}
