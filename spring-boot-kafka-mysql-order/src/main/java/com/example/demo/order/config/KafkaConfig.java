package com.example.demo.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

  public static final String ORDER_TOPIC = "order-topic";
  public static final String ORDER_DLQ = "order-dlq";

  @Bean
  public NewTopic orderTopic() {
    return TopicBuilder.name(ORDER_TOPIC)
        .partitions(3)
        .replicas(3)
        .build();
  }

  @Bean
  public NewTopic orderDLQ() {
    return TopicBuilder.name(ORDER_DLQ)
        .partitions(1)
        .replicas(1)
        .build();
  }
}    
