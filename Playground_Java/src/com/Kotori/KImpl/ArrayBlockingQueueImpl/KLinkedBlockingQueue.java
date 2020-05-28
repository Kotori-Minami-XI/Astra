package com.Kotori.KImpl.ArrayBlockingQueueImpl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KLinkedBlockingQueue<T>{
    private Condition notEmpty;
    private Condition notFull;
    private AtomicInteger count;
    private Lock takeLock;
    private Lock putLock;
    private int capacity;

    private KLinkedBlockingQueue.Node head;
    private KLinkedBlockingQueue.Node last;

    public KLinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
        count = new AtomicInteger(0);

        takeLock = new ReentrantLock();
        putLock = new ReentrantLock();

        notEmpty = takeLock.newCondition();
        notFull = putLock.newCondition();

        head = null;
        last = null;
    }

    public void put(T t) throws InterruptedException {
        putLock.lock();
        try {
            while (count.get() == capacity) {
                notFull.await();
            }
            this.enqueue(t);
            signalNotEmpty();
        } finally {
            putLock.unlock();
        }
    }

    public void take() throws InterruptedException {
        takeLock.lock();
        try {
            while (count.get() == 0) {
                notEmpty.await();
            }
            this.dequeue();
            signalNotFull();
        } finally {
            takeLock.unlock();
        }
    }

    private void enqueue(T t) {
        Node newNode = new KLinkedBlockingQueue.Node(t);
        if (null == last) {
            last = newNode;
            head = newNode;
        } else {
            last.next = newNode;
            last = last.next;
        }
        this.count.getAndIncrement();
        System.out.println(Thread.currentThread().getName() + "生产了一个Iron，现有"+ this.count +",还能存放的Iron为"+this.remainingCapacity());
    }

    private void dequeue() {
        Node nextNode = head.next;
        head.next = null;
        head = nextNode;
        if (head == null) {
            last = null;
        }
        this.count.getAndDecrement();
        System.out.println(Thread.currentThread().getName() + "消耗了一个Iron，现有"+ this.count +",还能存放的Iron为"+this.remainingCapacity());

    }

    private void signalNotEmpty() {
        this.takeLock.lock();
        try {
            this.notEmpty.signal();
        } finally {
            this.takeLock.unlock();
        }
    }

    private void signalNotFull() {
        this.putLock.lock();
        try {
            this.notFull.signal();
        } finally {
            this.putLock.unlock();
        }
    }

    private int remainingCapacity() {
        return this.capacity - this.count.get();
    }

    class Node {
        KLinkedBlockingQueue.Node next;
        T val;
        Node(T t) {
            val = t;
            next = null;
        }
    }
}
