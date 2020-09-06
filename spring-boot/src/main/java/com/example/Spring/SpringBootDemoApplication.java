package com.example.Spring;

import com.example.Spring.model.Book;
import com.example.Spring.service.BookService;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableAdminServer
@EnableScheduling
public class SpringBootDemoApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        //server.setPort(9000);
    }

    // nginx.conf redis-session测试
    // java -jar demo1.jar --server.port=8012
    // java -jar demo2.jar --server.port=8013

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

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

    @Bean
    public ApplicationRunner startupMailSender1(JavaMailSender javaMailSender) {
        return (args) -> javaMailSender.send((msg) ->
        {
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            helper.setTo("38761770@qq.com");
            helper.setFrom("807776962@qq.com");
            helper.setSubject("Status message-1");
            helper.setText("All is well.");
        });
    }

    @Bean
    public ApplicationRunner startupMailSender2(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        return (args) -> javaMailSender.send((msg) -> {
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            helper.setTo("38761770@qq.com");
            helper.setFrom("807776962@qq.com");
            helper.setSubject("Status message-2");

            Context context = new Context(LocaleContextHolder.getLocale(), Collections.singletonMap("msg", "All is well!"));
            String body = templateEngine.process("email.html", context);
            helper.setText(body, true);
        });
    }
}
