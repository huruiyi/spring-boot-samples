package com.example.util;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ProducerUtil {

  private static final String TOPIC = "topic-demo";
  private static final String BROKERLISTS = "localhost:9092";
  public static KeyedMessage<String, String> message = null;
  private static Properties properties = null;
  private static Producer<String, String> producer = null;

  static {
    properties = new Properties();
    properties.put("serializer.class", "kafka.serializer.StringEncoder");
    properties.put("metadata.broker.list", BROKERLISTS);

    ProducerConfig config = new ProducerConfig(properties);
    producer = new kafka.javaapi.producer.Producer<String, String>(config);
  }

  public static void producer(Object msg) {
    message = new KeyedMessage<String, String>(TOPIC, "message : " + msg);
    producer.send(message);
    System.out.println("send message : " + msg);
  }

  public static void main(String[] args) throws InterruptedException {
    while (true) {
      Thread.sleep(1000);
      producer("main 生产消息:" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    }
  }

}
