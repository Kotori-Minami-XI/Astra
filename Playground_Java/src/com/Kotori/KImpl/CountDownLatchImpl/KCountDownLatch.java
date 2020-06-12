package com.Kotori.KImpl.CountDownLatchImpl;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class KCountDownLatch {
    private AtomicInteger count;
    private ConcurrentLinkedQueue<Thread> waiters;

    public KCountDownLatch(Integer count) {
        if (count < 0) {
            throw new RuntimeException("count cannot be below 0");
        }
        this.count = new AtomicInteger(count);
        waiters = new ConcurrentLinkedQueue();
    }

    public Integer getCount() {
        return this.count.get();
    }

    public void countDown() {
        Integer c = this.count.decrementAndGet();
        if (c == 0 && !waiters.isEmpty()) {
            Thread t = waiters.poll();
            LockSupport.unpark(t);
        }
    }

    public void await() {
        if (this.count.get() == 0) {
            return;
        }
        this.waiters.add(Thread.currentThread());

        while (this.count.get() > 0) {
            LockSupport.park(Thread.currentThread());
        }
    }
}
