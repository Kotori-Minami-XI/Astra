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

        last = head = new Node(null);
    }

    public void put(T t) throws InterruptedException {
        putLock.lock();
        int c;
        try {
            while (count.get() == capacity) {
                notFull.await();
            }
            this.enqueue(t);

            c = this.count.getAndIncrement();
            System.out.println(Thread.currentThread().getName() + "生产了一个Iron，现有"+ (c+1) +",还能存放的Iron为"+this.remainingCapacity());

            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock();
        }

        if (c == 0) {
            signalNotEmpty();
        }
    }

    public T take() throws InterruptedException {
        takeLock.lock();
        T res = null;
        int c;
        try {
            while (count.get() == 0) {
                notEmpty.await();
            }
            res = this.dequeue();
            c = this.count.getAndDecrement();
            System.out.println(Thread.currentThread().getName() + "消耗了一个Iron，现有"+ (c-1) +",还能存放的Iron为"+this.remainingCapacity());

            if (c > 1) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }

        if (c == capacity) {
            signalNotFull();
        }
        return res;
    }

    private void enqueue(T t){
        Node newNode = new KLinkedBlockingQueue.Node(t);
        last.next = newNode;
        last = last.next;

        assert (last != null);
    }

    private T dequeue() {
        Node nextNode = head.next;
        head.next = head;
        head = nextNode;

        T res = (T)head.val;
        head.val = null;

        return res;
    }

    private void signalNotEmpty() {
        this.takeLock.lock();
        try {
            this.notEmpty.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.takeLock.unlock();
        }
    }

    private void signalNotFull() {
        this.putLock.lock();
        try {
            this.notFull.signal();
        } catch (Exception e) {
            e.printStackTrace();
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
