package com.example.demo.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
public class RedisConfig {

  @Bean
  public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
    // 锁过期时间设置为60秒，防止死锁
    return new RedisLockRegistry(redisConnectionFactory, "stock-lock", 60000);
  }

}    
