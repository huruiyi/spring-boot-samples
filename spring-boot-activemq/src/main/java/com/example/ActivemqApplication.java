package com.example;

import com.example.service.Msg;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@SpringBootApplication
public class ActivemqApplication implements CommandLineRunner {

  final JmsTemplate jmsTemplate;

  public ActivemqApplication(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(ActivemqApplication.class, args);
  }

  @Override
  public void run(String... args) {
    jmsTemplate.send("activemq-listener", new Msg());
  }
}
