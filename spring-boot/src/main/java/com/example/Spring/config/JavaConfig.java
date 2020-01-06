package com.example.Spring.config;

import com.example.Spring.service.SingleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public SingleService singleService() {
        return new SingleService();
    }
}