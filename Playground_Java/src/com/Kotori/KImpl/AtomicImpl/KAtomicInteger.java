package com.Kotori.KImpl.AtomicImpl;

public class KAtomicInteger {
    private Integer value;

    public KAtomicInteger() {
        value = 0;
    }

    public KAtomicInteger(Integer value) {
        this.value = value;
    }

    public Integer increaseAndGet() {
        synchronized (this) {
            this.value++;
            System.out.println("ThreadId=" + Thread.currentThread().getId() + " value=" + this.value);
            return this.value;
        }
    }
}
