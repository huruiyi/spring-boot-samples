package com.example.websocketsockjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SockJsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SockJsApplication.class, args);
    }

}
