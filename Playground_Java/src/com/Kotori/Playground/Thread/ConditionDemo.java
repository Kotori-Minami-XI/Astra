package com.Kotori.Playground.Thread;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    static int num =5;
    Lock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();

    @Test
    public void testCondition() throws InterruptedException {

        List<Thread> list =new ArrayList<>();
        for (int i=0;i<3;i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    produce();
                }
            }));
        }

        for (int i=0;i<2;i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    consume();
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }
    }

    private void produce() {
        while(true) {
            try {
                lock.lock();
                while (num >= 10) {
                    notFull.await();
                }

                Thread.sleep(300);
                num++;
                System.out.println(num);
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void consume(){
        while (true) {
            try {
                lock.lock();
                while (num == 0) {
                    notEmpty.await();
                }

                Thread.sleep(500);
                num--;
                System.out.println(num);
                notFull.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}


