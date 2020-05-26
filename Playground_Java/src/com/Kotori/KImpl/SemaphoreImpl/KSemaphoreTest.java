package com.Kotori.KImpl.SemaphoreImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class KSemaphoreTest {
    @Test
    public void testKSemaphore() throws InterruptedException {
        KSemaphore semaphore = new KSemaphore(3);

        ArrayList<Thread> list = new ArrayList<>();
        for (int i=0; i<10; i=i+1) {
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
    private KSemaphore semaphore;
    String name;

    public Dear(KSemaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(name + " 正在喝水");
            Thread.sleep(5000);
            System.out.println(name + " 完成了喝水---------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}