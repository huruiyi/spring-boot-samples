package com.example.demo.order.service;

import com.example.demo.order.config.KafkaConfig;
import com.example.demo.order.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class OrderProducer {

  private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
  private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

  public OrderProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendOrder(OrderDTO order) {
    logger.info("发送订单消息: {}", order);
    ListenableFuture<SendResult<String, OrderDTO>> future = kafkaTemplate.send(KafkaConfig.ORDER_TOPIC, order.getOrderNumber(), order);

    future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDTO>>() {
      @Override
      public void onSuccess(SendResult<String, OrderDTO> result) {
        logger.info("订单消息发送成功: {}", result.getProducerRecord().value());
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("订单消息发送失败: {}", ex.getMessage(), ex);
        // 这里可以添加重试逻辑或记录失败日志
      }
    });
  }
}    
