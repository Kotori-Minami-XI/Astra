package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    static AtomicInteger atomicInteger = new AtomicInteger();
    @Test
    public void test () throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i=0;i< 3;i++) {
            threads.add(new Thread(new Adder("Adder" + i)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}

class Adder implements Runnable{
    private String name;
    public Adder (String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            int value = AtomicIntegerDemo.atomicInteger.incrementAndGet();
            System.out.println(this.name + " value=" + value);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
