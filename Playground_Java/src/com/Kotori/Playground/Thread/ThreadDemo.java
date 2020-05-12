package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        // Runnable实现 可以实现资源共享
        Thread t1 = new Thread(new Thread1());
        t1.start();

        // Thread实现 不可以实现资源共享
        Thread t2 = new Thread(new Thread2());
        t2.start();

        // Callable实现 可以有返回值
        FutureTask<Integer> futureTask = new FutureTask<>(new Thread3());
        Thread t3 = new Thread(futureTask);
        t3.start();
        System.out.println(futureTask.get());

    }
}

class Thread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread1 -----------");
    }
}

class Thread2 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread2 -----------");
    }
}

class Thread3 implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println("Thread3-----------");
        return 5;
    }
}