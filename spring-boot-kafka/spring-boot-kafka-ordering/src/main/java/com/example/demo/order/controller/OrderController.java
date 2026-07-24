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
  public ResponseEntity<String> createOrder(@RequestBody OrderDTO order) {
    // 生成订单ID
    String orderId = UUID.randomUUID().toString();
    order.setOrderId(orderId);
    order.setCreateTime(LocalDateTime.now());
    order.setStatus("CREATED");

    // 发送订单消息到Kafka
    orderProducer.sendOrder(order);

    // 立即返回订单ID，无需等待订单处理完成
    return new ResponseEntity<>("订单已接收，ID: " + orderId, HttpStatus.ACCEPTED);
  }

}    
