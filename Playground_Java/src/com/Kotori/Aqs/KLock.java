package com.Kotori.Aqs;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.LockSupport;


public class KLock {
    // 记录当前加锁次数
    private volatile int state;

    // 当前取得锁的线程
    private Thread lockHolder;

    // 等待队列
    private ConcurrentLinkedDeque<Thread> waiters = new ConcurrentLinkedDeque();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    public Boolean acquire() {
        Thread currentThread = Thread.currentThread();
        int c = this.getState();
        if (c == 0) { // 当前同步器还没有被加锁
            if (waiters.size() == 0 || compareAndSwapState(0 ,1)) {
                this.setLockHolder(currentThread);
                return true;
            }
        }
        return false;
    }

    public void lock() {
        // 获取到锁，直接返回
        if (this.acquire()) {
            return;
        }
        Thread currentThread = Thread.currentThread();

        // 开始自旋等待
        while (true) {
            // 获取到锁，退出自旋
            if (this.acquire()) {
                break;
            }
            //阻塞当前线程
            LockSupport.park(currentThread);

        }

    }

    public void unlock() {

    }

    // 工具类获取Unsafe实例
    private static final Unsafe unsafe = UnsafeInstance.getUnsafeInstance();
    // State在类中的偏移
    private static Long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(KLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public Boolean compareAndSwapState(int expected, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expected, update);
    }



}
