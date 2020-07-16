package com.Kotori.Playground.Threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSingledThreadPoolDemo {
    /***
     * newSingleThreadExecutor：顾名思义是只有一个单线程的线程池，具体场景不太清晰，
     * 按照线程含义来看，适用于需要按顺序(FIFO, LIFO, 优先级)的执行任务的场景，
     * 以及thread confinement(变量只能由特定线程访问)的要求。
     *
     * 创建一个线程的线程池，在这个线程池中始终只有一个线程存在。如果线程池中的线程因为异常问题退出，
     * 那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
     * public static ExecutorService newSingleThreadExecutor() {
     *     return new FinalizableDelegatedExecutorService
     *         (new ThreadPoolExecutor(1, 1,
     *                                 0L, TimeUnit.MILLISECONDS,
     *                                 new LinkedBlockingQueue<Runnable>()));
     * }
     * // corePoolSize=1,maximumPoolSize=1,keepAliveTime=0s
     */
    @Test
    public void testBasicFunction() throws Exception {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i=0;i<10;i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("task");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.in.read();
    }
}
