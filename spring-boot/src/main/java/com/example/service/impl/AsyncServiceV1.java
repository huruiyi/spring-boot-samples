package com.example.service.impl;

import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AsyncServiceV1 {

  private Integer num = 0;

  public void reset() {
    num = 0;
  }

  @Async("myTaskAsyncPool")
  public Future<Long> doTask1(int i) throws InterruptedException {
    long sum = 0;
    Thread.sleep(2500L);

    for (int j = 0; j < 500000000; j++) {
      sum += j;
    }
    log.info("thread name is {}  sum= {}", Thread.currentThread().getName(), sum);
    log.info("Task {} started.", i);
    return new AsyncResult<>(sum);
  }

  @Async("myTaskAsyncPool")
  public void doTask2(int i) {
    long sum = 0;
    for (int j = 0; j < 500000000; j++) {
      sum += j;
    }
    log.info("thread name is {}  sum= {}", Thread.currentThread().getName(), sum);
    log.info("Task {} started.", i);
  }


  @Async("myTaskAsyncPool")
  public void doTask3() throws InterruptedException {
    Thread.sleep(500);
    log.info("当前第 {} 次执行.", num++);
  }


  @Async("myTaskAsyncPool")
  public Future<String> doTask4() throws InterruptedException {
    Thread.sleep(500);
    return new AsyncResult("当前第" + num++ + "次执行");
  }


  @Async
  public void asyncSendMail() throws InterruptedException {
    Thread.sleep(5 * 1000);
    log.info("测试@Async, 异步发送邮件等使用");
  }

}
