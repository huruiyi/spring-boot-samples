package com.example.service.unclassified;

import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncServiceV2 {

  @Async("getAsyncExecutor")
  public void async1() {
    log.info("异步任务 当前线程：{}", Thread.currentThread().getName());
  }

  @Async("getAsyncExecutor")
  public Future<Integer> async2() {
    log.info("异步任务 当前线程：{}", Thread.currentThread().getName());
    return new AsyncResult<>(999);

  }
}


