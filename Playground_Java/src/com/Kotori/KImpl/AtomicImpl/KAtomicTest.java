package com.Kotori.KImpl.AtomicImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KAtomicTest {
    @Test
    public void testKAtomicInteger() throws InterruptedException {
        KAtomicInteger kAtomicInteger = new KAtomicInteger();

        List<Thread> threads = new ArrayList<>();
        for (int i=0;i< 40;i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    kAtomicInteger.increaseAndGet();
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
