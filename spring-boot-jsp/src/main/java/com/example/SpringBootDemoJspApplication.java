package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringBootDemoJspApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoJspApplication.class, args);
    }
}
