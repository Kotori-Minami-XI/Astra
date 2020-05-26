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
                        System.out.println("t1准备生产，现有"+ queue.count+",还能存放的Iron为"+queue.remainingCapacity());
                        queue.put(new Iron());
                        System.out.println("t1生产了一个Iron，现有"+ queue.count+",还能存放的Iron为"+queue.remainingCapacity());
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
                        System.out.println("t2准备消耗，现有"+ queue.count+",还能存放的Iron为"+queue.remainingCapacity());
                        queue.take();
                        System.out.println("t2消耗了一个Iron，现有"+ queue.count +",还能存放的Iron为"+queue.remainingCapacity());
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