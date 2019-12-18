package com.example.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringDemo01 {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringDemo01.class, args);
        System.out.println("# Beans: " + ctx.getBeanDefinitionCount());

        String[] names = ctx.getBeanDefinitionNames();
        Arrays.sort(names);
        for (String name : names) {
            if (SpringDemo01.class.getSimpleName().toLowerCase().equals(name.toLowerCase())) {
                System.out.printf("***********************" + name + "*************************\n");
            } else {
                System.out.println(name);
            }
        }

        System.out.println();
        Arrays.asList(names).forEach(System.out::println);
    }
}
