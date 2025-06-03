package com.example;

import com.example.model.Book;
import com.example.service.BookService;
import com.example.service.game.GameRunnerV1;
import com.example.service.game.GameRunnerV2;
import com.example.service.game.service.GamingConsole;
import com.example.service.game.service.impl.MarioGame;
import com.example.service.impl.BinarySearchImpl;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@SpringBootApplication
public class SampleApplication extends SpringBootServletInitializer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

  @Value("${currency-service.url}")
  private String url;

  @Value("${currency-service.username}")
  private String username;

  @Value("${currency-service.key}")
  private String key;

  @Autowired
  private Environment environment;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SampleApplication.class, args);
    printBeans(context);

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

  private static void printBeans(ConfigurableApplicationContext context) {
    log.info("# Beans: {}", context.getBeanDefinitionCount());

    String[] names = context.getBeanDefinitionNames();
    Arrays.sort(names);
    Arrays.asList(names).forEach(log::info);
  }

  // nginx.conf redis-session测试
  // java -jar demo1.jar --server.port=8012
  // java -jar demo2.jar --server.port=8013
  // java -jar app.jar --spring.application.name=spring-boot
  // export SPRING_APPLICATION_NAME=spring-boot
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SampleApplication.class);
  }

  //jar包: 执行SpringBootApplication的run方法,启动IOC容器,然后创建嵌入式Servlet容器
  //war包: 先是启动Servlet服务器,服务器启动Springboot应用(springBootServletInitizer),然后启动IOC容器
  @Override
  public void customize(ConfigurableServletWebServerFactory server) {
    server.setPort(443);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);

      String appName = environment.getProperty("spring.application.name");
      System.out.println("应用名称: " + appName);
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
