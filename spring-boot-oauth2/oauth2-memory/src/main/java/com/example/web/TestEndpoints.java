package com.example.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndpoints {

  @GetMapping("/product/{productId}")
  public String getProduct(@PathVariable String productId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return "product id : " + productId + ",User Name:" + authentication.getName();
  }

  @GetMapping("/order/{orderId}")
  public String getOrder(@PathVariable String orderId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return "order id : " + orderId + ",User Name:" + authentication.getName();
  }

}
