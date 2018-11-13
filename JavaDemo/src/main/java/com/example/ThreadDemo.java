package com.example;

public class ThreadDemo {


    public static void main(String[] args) throws InterruptedException {

        class UserThread extends Thread {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "[" + Thread.currentThread().getId() + "]" + " Is Running"+" isInterrupted is"+Thread.currentThread().isInterrupted());
                }
                System.out.println(Thread.currentThread().isInterrupted());
            }
        }

        UserThread thread = new UserThread();
        thread.start();
        thread.sleep(20);
        thread.interrupt();
    }
}
