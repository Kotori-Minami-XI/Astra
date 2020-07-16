package com.Kotori.Playground.Threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolDemo {
    /***
     * newScheduleThreadPool ：如果所有线程均处于繁忙状态，对于新任务会进入DelayedWorkQueue队列中，
     * 这是一种按照超时时间排序的队列结构。这个线程的应用场景就很容易知道了， 需要周期性执行的任务使用该线程池
     *
     * 此线程池可以指定固定数量的线程来周期性的去执行。比如通过 scheduleAtFixedRate 或者 scheduleWithFixedDelay
     * 来指定周期时间。PS：另外在写定时任务时（如果不用 Quartz 框架），最好采用这种线程池来做，
     * 因为它可以保证里面始终是存在活的线程的。
     * public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
     *         return new ScheduledThreadPoolExecutor(corePoolSize);
     * }
     * public ScheduledThreadPoolExecutor(int corePoolSize) {
     *     super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
     *           new DelayedWorkQueue());
     * }
     */
    @Test
    public void testBasicFunction1() throws IOException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        // 延迟1秒后，每隔三秒执行一次。优先保证任务执行的频率
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds and execute every 3 seconds");
            }
        }, 1,3, TimeUnit.SECONDS);

        System.in.read();
    }

    @Test
    public void testBasicFunction2() throws IOException {
        // 延迟3秒后执行
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);

        System.in.read();
    }

    @Test
    public void testBasicFunction3() throws IOException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        // 延迟1秒后，每执行一次隔三秒。优先保证任务执行的间隔
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds and execute every 3 seconds");
            }
        }, 1,3, TimeUnit.SECONDS);

        System.in.read();
    }
}
