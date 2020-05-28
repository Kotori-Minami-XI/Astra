package com.Kotori.KImpl.ArrayBlockingQueueImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueueTest {
    @Test
    public void testKArrayBlockingQueue() throws InterruptedException {
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

    @Test
    public void testKLinkedBlockingQueue() throws InterruptedException {
        KLinkedBlockingQueue<Iron> queue = new KLinkedBlockingQueue(5);

        List<Thread> threads = new ArrayList();
        for (int i=0;i<1;i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            queue.put(new Iron());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }));
        }

        for (int i=0;i<1;i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            queue.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

    }

}

class Iron {

}