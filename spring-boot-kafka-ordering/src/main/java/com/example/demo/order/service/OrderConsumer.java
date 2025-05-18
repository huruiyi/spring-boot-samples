package com.example.demo.order.service;

import com.example.demo.order.config.KafkaConfig;
import com.example.demo.order.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

  private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

  @KafkaListener(topics = KafkaConfig.ORDER_TOPIC, groupId = "order-group")
  public void processOrder(OrderDTO order) {
    logger.info("收到订单消息: {}", order);

    try {
      // 模拟耗时的订单处理逻辑
      Thread.sleep(1000);

      // 处理订单，如库存扣减、支付处理、物流安排等
      logger.info("订单处理完成: {}", order.getOrderId());
    } catch (Exception e) {
      logger.error("订单处理失败: {}", e.getMessage(), e);
    }
  }

}
