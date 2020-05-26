package com.Kotori.KImpl.SemaphoreImpl;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class KSemaphore {
    private volatile AtomicInteger permits;
    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue();

    public KSemaphore(Integer permits) {
        this.permits = new AtomicInteger(permits);
    }

    public synchronized void acquire() {
        int c = this.permits.get();
        if (c > 0) {
            this.permits.getAndAdd(-1);
            return;
        }

        Thread currentThread = Thread.currentThread();
        waiters.add(currentThread);
        while (true) {
            if (this.permits.get() > 0 && waiters.peek() == currentThread) {
                this.permits.getAndAdd(-1);
                waiters.poll();
                return;
            }
            LockSupport.park(currentThread);
        }
    }

    public void release() {
        this.permits.getAndAdd(1);
        LockSupport.unpark(waiters.peek()); //唤醒队列第一个线程
    }
}
