package com.example.JavaConcurrency;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：RaceCondition
 * 日期：2019/5/26 14:46
 **/

// This class exposes a publicly accessible counter
// to help demonstrate race condition problem
class CounterRunnable {
    public static long count = 0;
}

// This class implements Runnable interface
// Its run method increments the counter three times
class UseCounterRunnable implements Runnable {
    public void increment() {
        // increments the counter and prints the value
        // of the counter shared between threads
        CounterRunnable.count++;
        System.out.print(CounterRunnable.count + "  ");
    }

    public void run() {
        increment();
        increment();
        increment();
    }
}

public class RaceCondition {

    public static void main(String args[]) {
        UseCounterRunnable c = new UseCounterRunnable();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        Thread t3 = new Thread(c);
        t1.start();
        t2.start();
        t3.start();
    }

}
