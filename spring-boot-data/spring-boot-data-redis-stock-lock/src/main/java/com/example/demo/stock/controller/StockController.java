package com.example.demo.stock.controller;

import com.example.demo.stock.entity.Product;
import com.example.demo.stock.repository.ProductRepository;
import com.example.demo.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private StockService stockService;

  @PostMapping("/init")
  public String initProduct(@RequestBody Product product) {
    productRepository.save(product);
    return "产品初始化成功";
  }

  @GetMapping("/check/{productId}")
  public Long checkStock(@PathVariable String productId) {
    return productRepository.findStockById(productId);
  }

  @PostMapping("/decrease/{productId}/{quantity}")
  public boolean decreaseStock(@PathVariable String productId, @PathVariable Long quantity) {
    return stockService.decreaseStock(productId, quantity);
  }

}    
