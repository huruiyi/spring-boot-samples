package com.example.spring;

import com.example.spring.service.HelloWorld;
import com.example.spring.service.HelloWorldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
    private static ApplicationContext context;

    @Test
    public void contextLoads() {
        context = new ClassPathXmlApplicationContext("beans.xml");

        HelloWorldService springService = (HelloWorldService) context.getBean("helloWorldSpringService");
        HelloWorld springhw = springService.getHelloWorld();
        springhw.sayHello();

        HelloWorldService strutsservice = (HelloWorldService) context.getBean("helloWorldStrutsService");
        HelloWorld servicehw = strutsservice.getHelloWorld();
        servicehw.sayHello();
    }

}
