package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueDemo {
    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        BlockingQueue<Cargo> queue = new ArrayBlockingQueue(5, false);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                       // System.out.println("t1准备生产，现有"+ queue.size()+",还能存放的cargo为"+queue.remainingCapacity());
                        queue.put(new Cargo());
                        System.out.println("t1生产了一个cargo，现有"+ queue.size()+",还能存放的cargo为"+queue.remainingCapacity());
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
                       // System.out.println("t2准备消耗，现有"+ queue.size()+",还能存放的cargo为"+queue.remainingCapacity());
                        queue.take();
                        System.out.println("t2消耗了一个cargo，现有"+ queue.size()+",还能存放的cargo为"+queue.remainingCapacity());
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
    public void testLinkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<Cargo> queue = new LinkedBlockingQueue(5);

        List<Thread> threads = new ArrayList();
        for (int i=0;i<2;i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            queue.put(new Cargo());
                            System.out.println(Thread.currentThread().getName()+"生产了一个cargo，现有"+ queue.size()+",还能存放的cargo为"+queue.remainingCapacity());
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
                            System.out.println(Thread.currentThread().getName()+"消耗了一个cargo，现有"+ queue.size()+",还能存放的cargo为"+queue.remainingCapacity());
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

class Cargo {

}