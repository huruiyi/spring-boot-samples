package com.example.config;

import com.example.enums.Gender;
import com.example.model.Person;
import com.example.service.impl.SingleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

  @Bean
  public SingleService singleService() {
    return new SingleService();
  }

  @Bean("configPerson")
  public Person person() {
    return new Person(1L, "ruiyi", "hu", 30, Gender.MALE);
  }

//  @Bean
//  public JavaMailSenderImpl JavaMailSender() {
//    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//    mailSender.setHost("smtp.qq.com");
//    mailSender.setUsername("807776962@qq.com");
//    String emailPassword = System.getenv("EmailPassword");
//    mailSender.setPassword(emailPassword);
//    return mailSender;
//  }

}
