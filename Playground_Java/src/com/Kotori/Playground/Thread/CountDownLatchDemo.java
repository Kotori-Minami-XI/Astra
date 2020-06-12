package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    @Test
    public void testCountDownLatch() throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("模块1开始运行");
                    Thread.sleep(3000);
                    System.out.println("模块1完成任务");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("模块2开始运行");
                    Thread.sleep(5000);
                    System.out.println("模块2完成任务");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("模块3开始运行");
                    Thread.sleep(2000);
                    System.out.println("模块3完成任务");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (countDownLatch.getCount() > 0) {
                        System.out.println("主模块等待中.....");
                    }
                    countDownLatch.await();
                    System.out.println("主模块开始运行");
                    Thread.sleep(2000);
                    System.out.println("主模块完成任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.in.read();
    }
}
