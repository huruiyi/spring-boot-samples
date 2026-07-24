package com.example.demo.order.repository;

import com.example.demo.order.entity.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findByOrderNumber(String orderNumber);
}    
