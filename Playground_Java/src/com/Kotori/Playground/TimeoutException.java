package com.Kotori.Playground;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

public class TimeoutException {
    /***
     * @brief
     * @throws IOException
     */
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

        ExecutorService service = Executors.newSingleThreadExecutor();

        try {
            Future future = service.submit(futureTask);
            System.out.println(futureTask.get());
            Thread.sleep(3000);
            isTargetMethodFinished = true;
            System.out.println("targetMethod---end");
        } catch (ExecutionException  e) {
            System.out.println("targetMethod--return");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            service.shutdown();
        }
    }
}
