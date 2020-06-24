package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

public class TimeoutMethodDemo {
    /***
     * @brief  方法超时就抛异常,并终止此方法
     * @throws IOException
     */
    @Test
    public void testTimeoutMethodDemo() throws IOException {
        FutureTask<String> futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("方法正在执行中...");
                Thread.sleep(3000);
                System.out.println("方法已经执行完毕---");
                return "成功返回";
            }
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(futureTask);

        try {
            String o = (String)futureTask.get(1000, TimeUnit.MILLISECONDS);
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("方法超时！终止运行");
            future.cancel(true);
            e.printStackTrace();
        } finally {
            executor.shutdown();
            System.in.read();
        }

    }
}
