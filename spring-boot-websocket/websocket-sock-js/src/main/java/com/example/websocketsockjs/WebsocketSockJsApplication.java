package com.example.websocketsockjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class WebsocketSockJsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketSockJsApplication.class, args);
    }

}
