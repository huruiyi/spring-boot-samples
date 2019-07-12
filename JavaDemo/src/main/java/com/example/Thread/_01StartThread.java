package com.example.Thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

import com.sun.management.ThreadMXBean;

public class _01StartThread {

	public class ThRuner implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread() + "" + i);
			}
		}
	}

	private static Runnable ThRuner = null;

	public static void Demo1() {
		ThRuner = new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					if (i % 5 == 0) {
						System.out.println();
					} else {
						System.out.print(i + "__" + Thread.currentThread() + "" + i + "\t");
					}
				}
			}
		};
		Thread thread1 = new Thread(ThRuner);
		thread1.setName("1号");
		Thread thread2 = new Thread(ThRuner);
		thread2.setName("2号");
		Thread thread3 = new Thread(ThRuner);
		thread3.setName("3号");
		Thread thread4 = new Thread(ThRuner);
		thread4.setName("4号");
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}

	public static void Demo2() {
		ThreadMXBean threadMXBean = (ThreadMXBean) ManagementFactory.getThreadMXBean();
		ThreadInfo[] dumpAllThreads = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo info : dumpAllThreads) {
			System.out.println(info.getThreadId() + " " + info.getThreadName());
		}
	}

	public static void main(String[] args) {
		Demo1();
		Demo2();
	}

}
