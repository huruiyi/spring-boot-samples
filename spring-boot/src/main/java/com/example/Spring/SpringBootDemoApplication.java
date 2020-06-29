package com.example.Spring;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringBootDemoApplication {

    // nginx.conf redis-session测试
    // java -jar demo1.jar --server.port=8012
    // java -jar demo2.jar --server.port=8013

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
