package com.Kotori.Playground.DeadLock;

import org.junit.Test;

public class DeadlockDemo {

    /***
     * @brief  两个线程死锁场景
     * @throws InterruptedException
     */
    @Test
    public void testDeadlock() throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1--start");
                synchronized (lock1) {
                    System.out.println("t1获取了lock1");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("t1获取了lock2");
                    }
                }
                System.out.println("t1--end");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2--start");
                synchronized (lock2) {
                    System.out.println("t2获取了lock2");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("t2获取了lock1");
                    }
                }
                System.out.println("t2--end");
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
