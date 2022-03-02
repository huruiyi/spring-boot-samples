package com.example.Concurrent;

public class AtomicIntegerTest {
    private static final int THREAD_COUNT = 20;
    public static int count = 0;

    public static void increase() {
        count++;
    }

    //https://blog.csdn.net/fanrenxiang/article/details/80623884
    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            System.out.println(Thread.activeCount());
            Thread.yield();
        }
        System.out.println(count);
    }
}
