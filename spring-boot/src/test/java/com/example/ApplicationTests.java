package com.example;

import com.example.model.Person;
import com.example.service.SimpleService;
import com.example.service.impl.HelloWorldService;
import com.example.service.impl.PersonService;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ApplicationTests {

  private static ApplicationContext context;

  @Autowired
  private PersonService personService;

  @Test
  void helloWorldSpringServiceTest() {
    context = new ClassPathXmlApplicationContext("beans.xml");

    HelloWorldService springService = (HelloWorldService) context.getBean("helloWorldSpringService");
    SimpleService helloWorld = springService.getHelloWorld();
    helloWorld.sayHello();
    Assertions.assertNotNull(helloWorld);
  }

  @Test
  void Test2() {
    RestTemplate restTemplate = new RestTemplate();
    for (int i = 0; i < 10; i++) {
      String result = restTemplate.getForObject("http://localhost:9000/rate/testLimit", String.class);
      System.out.println(result);
    }
  }

  @Test
  void configPersonTest() {
    Person person = (Person) context.getBean("configPerson");
    Assertions.assertNotNull(person);
  }

  @Test
  void userTest() {
    Person person = personService.getUserInfo();
    Assertions.assertEquals(person.getLastName(), "hu");
    Assertions.assertEquals(30, person.getAge());
  }

  @Test
  public void getEmailTest() {
    Properties properties = System.getProperties();
    Assertions.assertNotNull(properties);
    String emailPassword = System.getenv("EmailPassword");
    Assertions.assertNotNull(emailPassword);
  }
}
