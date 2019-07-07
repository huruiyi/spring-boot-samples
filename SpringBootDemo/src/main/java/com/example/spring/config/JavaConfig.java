package com.example.spring.config;

import com.example.spring.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public HelloService getHelloService() {
        return new HelloService();
    }
}