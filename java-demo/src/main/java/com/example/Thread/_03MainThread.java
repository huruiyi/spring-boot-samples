package com.example.Thread;

public class _03MainThread {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
            }
        };
        thread.start();
        Thread.sleep(100);
        thread.stop();
        System.out.println("B");
    }
}
