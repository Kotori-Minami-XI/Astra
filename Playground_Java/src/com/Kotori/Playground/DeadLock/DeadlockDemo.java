package com.Kotori.Playground.DeadLock;

import org.junit.Test;

public class DeadlockDemo {
    Integer resource_1 = 0;
    Integer resource_2 = 0;

    /***
     * @brief  两个线程死锁场景
     * @throws InterruptedException
     */
    @Test
    public void testDeadlock() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1-----start");
                while (true) {
                    if (resource_2 == 1) {
                        break;
                    }
                }
                resource_1 = 1;
                System.out.println("t1-----end");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2-----start");
                while (true) {
                    if (resource_1 == 1) {
                        break;
                    }
                }
                resource_2 = 1;
                System.out.println("t2-----end");
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
