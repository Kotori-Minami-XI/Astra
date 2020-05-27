package com.Kotori.KImpl.ArrayBlockingQueueImpl;

import org.junit.Test;

public class KArrayBlockingQueueTest {
    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        KArrayBlockingQueue<Iron> queue = new KArrayBlockingQueue(5);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        queue.put(new Iron());
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        queue.take();
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}

class Iron {

}