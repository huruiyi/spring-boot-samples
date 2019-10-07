package com.example.Concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomIntegerTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        AtomIntegerIncr atomIntegerIncr = new AtomIntegerIncr(countDownLatch);

        for (int i = 0; i < 100; i++) {
            new Thread(atomIntegerIncr).start();
            countDownLatch.countDown();
        }
    }
}

class AtomIntegerIncr implements Runnable {

    AtomicInteger atomicInteger = new AtomicInteger(0);
    CountDownLatch countDownLatch;

    public AtomIntegerIncr() {
    }

    public AtomIntegerIncr(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            int i = atomicInteger.get();
            countDownLatch.await();
            atomicInteger.set(i + 1);
            System.out.println(atomicInteger.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}