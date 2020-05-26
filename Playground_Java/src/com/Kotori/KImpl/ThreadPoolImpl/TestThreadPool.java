package com.Kotori.KImpl.ThreadPoolImpl;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

public class TestThreadPool {

    public static void main(String[] args) {
        KThreadPoolExecutor executor = new KThreadPoolExecutor(
                3,10, 1000L, true, new LinkedBlockingQueue());

        for (int i=0;i<10;i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在做业务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "完成了业务----------");
                }
            });
        }

    }
}
