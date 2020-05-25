package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    @Test
    public void testLock() throws InterruptedException {
        List<Thread> list = new ArrayList<>();

        Module module = new Module();
        for (int i=0;i<1000;i++) {
            list.add(new Thread(module));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }

        System.out.println(Module.count);
    }

    @Test
    public void testInterrupt() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.tryLock(10000, TimeUnit.MILLISECONDS);
                    System.out.println("t1获取锁");
                    Thread.sleep(4000);
                    System.out.println("t1释放锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.tryLock(10000, TimeUnit.MILLISECONDS);
                    System.out.println("t2获取锁");
                    Thread.sleep(2000);
                    System.out.println("t2释放锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();;
        t2.join();
    }

}


class Module implements Runnable{
    public static Integer count = 0;
    public Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lock();
            Module.count++;
        } finally {
            lock.unlock();
        }
    }
}

