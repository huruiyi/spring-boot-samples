package com.example.demo.stock.repository;

import com.example.demo.stock.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  private static final String PRODUCT_KEY_PREFIX = "product:";

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  public void save(Product product) {
    redisTemplate.opsForValue().set(PRODUCT_KEY_PREFIX + product.getProductId(), product.getStock());
  }

  public Long findStockById(String productId) {
    Object o = redisTemplate.opsForValue().get(PRODUCT_KEY_PREFIX + productId);
    if (o != null) {
      return Long.valueOf(o.toString()) ;
    }
    return 0L;
  }

  public boolean decreaseStock(String productId, Long quantity) {
    String key = PRODUCT_KEY_PREFIX + productId;
    Long stock = redisTemplate.opsForValue().decrement(key, quantity);
    if (stock != null) {
      return stock.compareTo(0L) >= 0;
    }
    return false;
  }

}    
