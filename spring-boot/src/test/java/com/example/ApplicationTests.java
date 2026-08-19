package com.example;

import com.example.model.Greeting;
import com.example.model.Person;
import com.example.service.SimpleService;
import com.example.service.game.GameRunnerV1;
import com.example.service.game.GameRunnerV2;
import com.example.service.game.service.GamingConsole;
import com.example.service.game.service.impl.MarioGame;
import com.example.service.impl.BinarySearchImpl;
import com.example.service.impl.HelloWorldService;
import com.example.service.impl.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Slf4j
@SpringBootTest
class ApplicationTests {

  private static ApplicationContext context;

  @Autowired
  private PersonService personService;

  @Test
  void envTest() {
    String localRedisPwd = System.getenv("LOCAL_REDIS_PWD");
    log.debug("从环境变量获取Redis的密码：{},密码长度：{}", localRedisPwd, localRedisPwd.length());
  }

  @Test
  void test(){
    BinarySearchImpl binarySearch = context.getBean(BinarySearchImpl.class);
    log.info(String.valueOf(binarySearch.binarySearch(new int[]{12, 4, 6})));

    GamingConsole game2 = new MarioGame();
    GameRunnerV1 gameRunnerV1 = new GameRunnerV1(game2);
    gameRunnerV1.run();

    GameRunnerV2 gameRunnerV2 = context.getBean(GameRunnerV2.class);
    gameRunnerV2.run();

    GameRunnerV1 runnerV1 = context.getBean(GameRunnerV1.class);
    runnerV1.run();
  }

  @Test
  void helloWorldSpringServiceTest() {
    context = new ClassPathXmlApplicationContext("beans.xml");

    HelloWorldService springService = (HelloWorldService) context.getBean("helloWorldSpringService");
    SimpleService helloWorld = springService.getHelloWorld();
    helloWorld.sayHello();
    Assertions.assertNotNull(helloWorld);
  }

  @Test
  void test1() {
    RestTemplate restTemplate = new RestTemplate();
    for (int i = 0; i < 10; i++) {
      String result = restTemplate.getForObject("http://localhost:9000/rate/testLimit", String.class);
      System.out.println(result);
    }
  }

  @Test
  public void test2() {
    RestTemplate restTemplate = new RestTemplate();
    Greeting greeting = restTemplate.getForObject("http://localhost:8086/greeting", Greeting.class);
    System.out.println(greeting.toString());
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
