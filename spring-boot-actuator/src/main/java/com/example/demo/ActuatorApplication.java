package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ActuatorApplication {

    /**
     * http://localhost:20200/
     * http://localhost:20300/manage/
     */
    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

}
