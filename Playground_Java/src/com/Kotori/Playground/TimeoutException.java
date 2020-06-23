package com.Kotori.Playground;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TimeoutException {
    @Test
    public void testTimeoutException() throws IOException {
        targetMethod();
        System.in.read();
    }
    static Boolean isTargetMethodFinished = false;
    public void targetMethod() {
        System.out.println("targetMethod---start");

        long startTime = System.currentTimeMillis();

        FutureTask<Boolean> futureTask = new FutureTask<>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                System.out.println("timer---start");
                long currentTime = System.currentTimeMillis();
                while (!isTargetMethodFinished && currentTime - startTime < 2000) {
                    currentTime = System.currentTimeMillis();
                }
                if (!isTargetMethodFinished) {
                    System.out.println("error");
                    throw new Exception("time out !!!!!!!!!!!");
                }
                System.out.println("timer---end");
                return true;
            }
        });
        Thread timer = new Thread(futureTask);

        try {
            timer.start();
            Thread.sleep(3000);
            isTargetMethodFinished = true;
            System.out.println("targetMethod---end");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("targetMethod--return");
            return;
        }
    }
}
