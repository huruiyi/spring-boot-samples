package com.example.service.impl;

import com.example.service.IRedisService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class RedisService implements IRedisService {

  @Resource(name = "redisTemplate")
  private ListOperations<String, String> messageList;

  @Resource(name = "redisTemplate")
  private RedisOperations<String, String> latestMessageExpiration;

  @Override
  public void addMessage(String user, String message) {
    messageList.leftPush(user, message);

    ZonedDateTime zonedDateTime = ZonedDateTime.now();
    Date date = Date.from(zonedDateTime.plus(20, ChronoUnit.SECONDS).toInstant());
    latestMessageExpiration.expireAt(user, date);
  }

  @Override
  public List<String> listMessages(String user) {
    return messageList.range(user, 0, -1);
  }
}
