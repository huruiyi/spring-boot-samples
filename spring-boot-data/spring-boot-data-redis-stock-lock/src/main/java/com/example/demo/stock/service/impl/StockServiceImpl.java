package com.example.demo.stock.service.impl;

import com.example.demo.stock.repository.ProductRepository;
import com.example.demo.stock.service.StockService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private RedisLockRegistry redisLockRegistry;

  @Override
  public boolean decreaseStock(String productId, Long quantity) {
    // 获取产品锁，使用产品ID作为锁的key
    Lock lock = redisLockRegistry.obtain(productId);
    try {
      if (lock.tryLock(10, TimeUnit.SECONDS)) {
        try {
          // 检查库存是否充足
          Long currentStock = productRepository.findStockById(productId);
          if (currentStock < quantity) {
            return false;
          }
          return productRepository.decreaseStock(productId, quantity);
        } finally {
          lock.unlock();
        }
      } else {
        // 获取锁失败
        return false;
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return false;
    }
  }
}    
