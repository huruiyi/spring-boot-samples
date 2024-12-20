package com.example;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "mysql.properties", autoRefreshed = true)
public class Demo3Application {

  public static void main(String[] args) {
    SpringApplication.run(Demo3Application.class, args);
  }

}
