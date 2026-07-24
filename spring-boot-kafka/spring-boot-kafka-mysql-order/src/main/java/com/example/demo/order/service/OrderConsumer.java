package com.example.demo.order.service;

import com.example.demo.order.config.KafkaConfig;
import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderConsumer {

  private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
  private final OrderRepository orderRepository;

  public OrderConsumer(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Transactional
  @KafkaListener(topics = KafkaConfig.ORDER_TOPIC, groupId = "order-group")
  public void processOrder(OrderDTO orderDTO, Acknowledgment acknowledgment) {
    logger.info("收到订单消息: {}", orderDTO);

    try {
      // 检查订单是否已存在（幂等性保障）
      if (orderRepository.findByOrderNumber(orderDTO.getOrderNumber()).isPresent()) {
        logger.warn("订单已存在，跳过处理: {}", orderDTO.getOrderNumber());
        acknowledgment.acknowledge();
        return;
      }

      // 转换DTO为实体
      Order order = new Order();
      BeanUtils.copyProperties(orderDTO, order);
      order.setCreateTime(orderDTO.getCreateTime());
      order.setUpdateTime(orderDTO.getCreateTime());

      // 保存订单到数据库
      orderRepository.save(order);

      // 手动确认消息
      acknowledgment.acknowledge();
      logger.info("订单处理完成: {}", order.getOrderNumber());

    } catch (Exception e) {
      logger.error("订单处理失败: {}", e.getMessage(), e);
      // 这里可以添加重试逻辑或发送到死信队列
      throw e; // 让Kafka自动重试
    }
  }
}    
