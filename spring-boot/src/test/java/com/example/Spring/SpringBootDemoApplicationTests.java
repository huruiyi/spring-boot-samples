package com.example.Spring;

import com.example.Spring.service.HelloWorld;
import com.example.Spring.service.unclassified.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class SpringBootDemoApplicationTests {
    private static ApplicationContext context;

    @Test
    public void Test1() {
        context = new ClassPathXmlApplicationContext("beans.xml");

        HelloWorldService springService = ( HelloWorldService ) context.getBean("helloWorldSpringService");
        HelloWorld springhw = springService.getHelloWorld();
        springhw.sayHello();

    }

    @Test
    public void Test2() {
//        RestTemplate restTemplate = new RestTemplate();
//        for (int i = 0; i < 5; i++) {
//            CusResult result = restTemplate.getForObject("http://localhost:9102/limit3", CusResult.class);
//            System.out.println(result);
//        }
    }

}
