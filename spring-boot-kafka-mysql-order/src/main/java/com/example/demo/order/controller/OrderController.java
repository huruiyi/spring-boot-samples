package com.example.demo.order.controller;

import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.service.OrderProducer;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderProducer orderProducer;

  public OrderController(OrderProducer orderProducer) {
    this.orderProducer = orderProducer;
  }

  @PostMapping
  public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
    // 生成唯一订单号
    String orderNumber = UUID.randomUUID().toString();
    orderDTO.setOrderNumber(orderNumber);
    orderDTO.setCreateTime(LocalDateTime.now());
    orderDTO.setStatus("CREATED");

    // 发送订单消息到Kafka（异步处理）
    orderProducer.sendOrder(orderDTO);

    // 立即返回订单号，无需等待数据库操作完成
    return new ResponseEntity<>("订单已接收，ID: " + orderNumber, HttpStatus.ACCEPTED);
  }
}    
