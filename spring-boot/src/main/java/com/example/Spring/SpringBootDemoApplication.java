package com.example.Spring;

import com.example.Spring.model.Book;
import com.example.Spring.service.BookService;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAdminServer
@EnableScheduling
public class SpringBootDemoApplication {

    // nginx.conf redis-session测试
    // java -jar demo1.jar --server.port=8012
    // java -jar demo2.jar --server.port=8013

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }


    @Bean
    public ApplicationRunner booksInitializer(BookService bookService) {
        return args -> {
            bookService.create(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
            bookService.create(new Book("9780451524935", "1984", "George Orwell"));
            bookService.create(new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));
        };
    }
}
