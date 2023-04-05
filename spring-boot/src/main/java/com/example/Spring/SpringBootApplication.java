package com.example.Spring;

import com.example.Spring.model.Book;
import com.example.Spring.service.BookService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Arrays;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication extends SpringBootServletInitializer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootApplication.class);
    }

    //jar包: 执行SpringBootApplication的run方法,启动IOC容器,然后创建嵌入式Servlet容器
    //war包: 先是启动Servlet服务器,服务器启动Springboot应用(springBootServletInitizer),然后启动IOC容器
    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        //server.setPort(9000);
    }

    // nginx.conf redis-session测试
    // java -jar demo1.jar --server.port=8012
    // java -jar demo2.jar --server.port=8013

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootApplication.class, args);
        //printBeans(context);
    }

    private static void printBeans(ConfigurableApplicationContext context) {
        System.out.println("# Beans: " + context.getBeanDefinitionCount());

        String[] names = context.getBeanDefinitionNames();
        Arrays.sort(names);
        for (String name : names) {
            if (SpringBootApplication.class.getSimpleName().equalsIgnoreCase(name)) {
                System.out.printf("***********************" + name + "*************************\n");
            } else {
                System.out.println(name);
            }
        }

        System.out.println();
        Arrays.asList(names).forEach(System.out::println);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
        };
    }

    @Bean
    public ApplicationRunner booksInitializer(BookService bookService) {
        return args -> {
            bookService.create(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
            bookService.create(new Book("9780451524935", "1984", "George Orwell"));
            bookService.create(new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));
        };
    }

    //public ApplicationRunner startupMailSender1(JavaMailSender javaMailSender)

    @Bean
    public ApplicationRunner startupMailSender1() {
        return null;
    }

    @Bean
    public ApplicationRunner startupMailSender2(SpringTemplateEngine templateEngine) {
        return null;
    }
}
