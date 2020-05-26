package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    @Test
    public void testSemaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3,true);

        List<Thread> list = new ArrayList<>();
        for (int i=0;i<10;i++) {
            list.add(new Thread(new Dear(semaphore,"Dear" + i)));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }
    }
}

class Dear implements Runnable {
    private Semaphore semaphore;
    String name;

    public Dear(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(name + "正在喝水");
            Thread.sleep(3000);
            System.out.println(name + "完成了喝水");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
