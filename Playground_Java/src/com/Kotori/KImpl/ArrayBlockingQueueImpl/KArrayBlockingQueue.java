package com.Kotori.KImpl.ArrayBlockingQueueImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KArrayBlockingQueue<T> {
    private List<T> items;
    private int capacity;
    private int putIndex;
    private int takeIndex;
    int count;

    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;

    public KArrayBlockingQueue(int capacity) {
        lock = new ReentrantLock(false);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();

        putIndex = 0;
        takeIndex = 0;
        count = 0;

        this.capacity = capacity;
        items = new ArrayList<T>(capacity);
        for (int i=0; i < capacity; i++) {
            items.add(null);
        }
    }

    public T take() throws InterruptedException {
        T res = null;
        try {
            this.lock.lock();
            while (count == 0) {
                this.notEmpty.await();
            }
            res = this.dequeue();
            notFull.signal();

        } finally {
            this.lock.unlock();
        }
        return res;
    }

    private T dequeue() {
        T res = this.items.get(takeIndex);
        this.items.set(takeIndex++, null);
        if (takeIndex == capacity) {
            takeIndex = 0;
        }
        this.count--;
        return res;
    }

    public void put(T item) throws InterruptedException {
        try{
            this.lock.lock();
            while (count == capacity) {
                notFull.await();
            }
            this.enqueue(item);
            notEmpty.signal();
        } finally {
            this.lock.unlock();
        }
    }

    private void enqueue(T item) {
        this.items.set(putIndex++, item);
        if (putIndex == capacity) {
            putIndex = 0;
        }
        this.count++;
    }

    public int remainingCapacity() {
        return this.capacity - this.count;
    }
}
