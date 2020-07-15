package com.Kotori.Playground.Threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolDemo {
    @Test
    public void testBasicFunction1() throws IOException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        // 延迟1秒后，每隔三秒执行一次
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
}
