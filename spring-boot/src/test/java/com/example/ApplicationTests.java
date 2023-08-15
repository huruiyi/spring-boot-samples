package com.example;

import com.example.model.CusResult;
import com.example.model.Person;
import com.example.service.HelloWorld;
import com.example.service.unclassified.HelloWorldService;
import com.example.service.unclassified.PersonService;
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
    HelloWorld helloWorld = springService.getHelloWorld();
    helloWorld.sayHello();
    Assertions.assertNotNull(helloWorld);
  }

  @Test
  void Test2() {
    RestTemplate restTemplate = new RestTemplate();
    for (int i = 0; i < 5; i++) {
      CusResult result = restTemplate.getForObject("http://localhost:9102/limit3", CusResult.class);
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

  @org.junit.Test
  public void getEmailTest() {
    Properties properties = System.getProperties();
    Assertions.assertNotNull(properties);
    String emailPassword = System.getenv("EmailPassword");
    Assertions.assertNotNull(emailPassword);
  }
}
