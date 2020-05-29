package com.Kotori.KImpl.ArrayBlockingQueueImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueueTest {
    @Test
    public void testKArrayBlockingQueue() throws InterruptedException {
        KArrayBlockingQueue<Iron> queue = new KArrayBlockingQueue(10);

        List<Thread> threads = new ArrayList();

        for (int i=0;i<3;i++) {
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

        for (int i=0;i<2;i++) {
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

    @Test
    public void testKLinkedBlockingQueue() throws InterruptedException {
        KLinkedBlockingQueue<Iron> queue = new KLinkedBlockingQueue(10);

        List<Thread> threads = new ArrayList();
        for (int i=0;i<2;i++) {
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

        for (int i=0;i<3;i++) {
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