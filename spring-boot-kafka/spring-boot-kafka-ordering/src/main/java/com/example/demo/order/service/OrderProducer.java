package com.example.demo.order.service;

import com.example.demo.order.config.KafkaConfig;
import com.example.demo.order.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

  private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
  private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

  public OrderProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendOrder(OrderDTO order) {
    logger.info("发送订单消息: {}", order);
    kafkaTemplate.send(KafkaConfig.ORDER_TOPIC, order.getOrderId(), order)
        .addCallback(
            success -> logger.info("订单消息发送成功: {}", success.getProducerRecord().value()),
            failure -> logger.error("订单消息发送失败: {}", failure.getMessage())
        );
  }

}
