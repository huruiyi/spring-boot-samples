package com.example.Concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomIntegerIncrTest {

  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(100);
    AtomIntegerIncr atomIntegerIncr = new AtomIntegerIncr(countDownLatch);

    for (int i = 0; i < 100; i++) {
      new Thread(atomIntegerIncr).start();
      countDownLatch.countDown();
    }
  }
}