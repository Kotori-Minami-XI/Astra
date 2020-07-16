package com.Kotori.Playground.Threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolDemo {
    /***
     * newFixedThreadPool是基于无界队列的线程池， 维护的线程数量是固定的，如果线程均在繁忙状态， 则新任务会放入无界队列里。
     * 适用于长期任务。
     * 如果用于短期任务，任务数量 < 线程池数量时，性能不会跟newCachedThreadPool太大的区别，
     * 但是超出时， 因为是短期的， 所以任务会不断的被放入队列，又被取出， 时间间隔很短
     * 并且过多的短期任务放入队列中，回使得内存吃紧，当任务数量过多时会造成很大的资源浪费。
     *
     *
     * 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
     * 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
     * public static ExecutorService newFixedThreadPool(int nThreads) {
     *     return new ThreadPoolExecutor(nThreads, nThreads,
     *                                   0L, TimeUnit.MILLISECONDS,
     *                                   new LinkedBlockingQueue<Runnable>());
     * }
     */
    @Test
    public void testBasicFunction1() throws IOException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        while (true) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " doing task...");
                }
            });
            Thread.sleep(1000);
        }
    }
}
