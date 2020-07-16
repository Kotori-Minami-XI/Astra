package com.Kotori.Playground.Threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolDemo {
    /***
     * newCachedThreadPool：顾名思义是一种可缓存的线程池， 线程池除了维护初始大小的线程外，当任务数量超出线程池大小时，
     * 便会新建线程， 而且当线程完成任务之后不会马上销毁，而是会保留一段时间(默认60s)，这种极大的减少了线程创建和销毁的资源消耗，
     * 当这种线程池的弊端是 线程最大值过大， 如果用于高并发且任务较长场景，很容易将内存全部吃光。 所以使用于执行短期异步小任务，
     * 或者并发量不高的场景。
     *
     * 可根据实际情况，调整线程数量的线程池，线程池中的线程数量不确定，
     * 如果有空闲线程会优先选择空闲线程，如果没有空闲线程并且此时有任务提交会创建新的线程。
     * 在正常开发中并不推荐这个线程池，因为在极端情况下，会因为 newCachedThreadPool 创建过多线程而耗尽 CPU 和内存资源。
     *
     * public static ExecutorService newCachedThreadPool() {
     *     return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                   60L, TimeUnit.SECONDS,
     *                                   new SynchronousQueue<Runnable>());
     * }
     * // corePoolSize=0,maximumPoolSize=Integer.MAX_VALUE,keepAliveTime=60s
     */
    @Test
    public void testBasicFunction() throws IOException {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0;i<100;i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("task");
                }
            });
        }
        System.in.read();
    }
}
