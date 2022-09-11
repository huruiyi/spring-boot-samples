package com.example.Spring.config;

import com.example.Spring.model.Person;
import com.example.Spring.service.unclassified.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class JavaConfig {
    @Bean
    public SingleService singleService() {
        return new SingleService();
    }

    @Bean("configPerson")
    public Person person() {
        Person user = new Person();
        user.setFirstName("San");
        user.setLastName("Zhang");
        user.setAge(18);
        return user;
    }

    @Bean
    public JavaMailSenderImpl JavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("807776962@qq.com");
        String emailPassword = System.getenv ("EmailPassword");
        mailSender.setPassword(emailPassword);
        return  mailSender;
    }

}