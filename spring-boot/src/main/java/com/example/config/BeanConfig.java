package com.example.config;

import com.example.enums.Gender;
import com.example.model.Book;
import com.example.model.Person;
import com.example.service.BookService;
import com.example.service.impl.SingleService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

  @Bean
  public ApplicationRunner booksInitializer(BookService bookService) {
    return args -> {
      bookService.create(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
      bookService.create(new Book("9780451524935", "1984", "George Orwell"));
      bookService.create(new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));
    };
  }

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
