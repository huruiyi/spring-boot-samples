package com.example.Spring.config;

import com.example.Spring.service.unclassified.SingleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaConfig {
    @Bean
    public SingleService singleService() {
        return new SingleService();
    }




//    @Bean
//    public JavaMailSenderImpl JavaMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.qq.com");
//        mailSender.setUsername("xxxxxxx@qq.com");
//        mailSender.setPassword("xxxxxxx");
//        return  mailSender;
//    }

}