package com.example.component;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AsyncService {

  public static volatile AtomicInteger i = new AtomicInteger(0);
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Async("myTaskAsyncPool")
  public Future<Long> doTask1(int i) {
    long sum = 0;
    try {
      Thread.sleep(2500L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (int j = 0; j < 500000000; j++) {
      sum += j;
    }
    System.out.println("thread name is" + Thread.currentThread().getName() + "    sum=" + sum);
    logger.info("Task" + i + " started.");
    return new AsyncResult<Long>(sum);
  }

  @Async("myTaskAsyncPool")
  public void doTask2(int i) {
    long sum = 0;
    for (int j = 0; j < 500000000; j++) {
      sum += j;
    }
    System.out.println("thread name is" + Thread.currentThread().getName() + "    sum=" + sum);
    logger.info("Task" + i + " started.");
  }


  @Async("myTaskAsyncPool")
  public void doTask3() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("当前第" + i.incrementAndGet() + "次执行");
  }


  @Async("myTaskAsyncPool")
  public Future<String> doTask4() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new AsyncResult("当前第" + i.incrementAndGet() + "次执行");
  }


  @Async
  public void asyncSendMail() {
    try {
      Thread.sleep(5 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("测试@Async, 异步发送邮件等使用");
  }

}