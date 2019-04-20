package springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springdemo.hello.HelloWorld;
import springdemo.hello.HelloWorldService;

public class App {
    private static ApplicationContext context;

    public static void main(String[] args) {

        context = new ClassPathXmlApplicationContext("beans.xml");

        HelloWorldService springService = (HelloWorldService) context.getBean("helloWorldSpringService");
        HelloWorld springhw = springService.getHelloWorld();
        springhw.sayHello();

        HelloWorldService strutsservice = (HelloWorldService) context.getBean("helloWorldStrutsService");
        HelloWorld servicehw = strutsservice.getHelloWorld();
        servicehw.sayHello();
    }
}