package com.Kotori.Playground.Thread;


import org.junit.Test;

import java.util.concurrent.*;

class Durant implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        while (true) {
        }
    }
}

class Curry implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        while (true) {
        }
    }
}

public class CallableDemo {

    @Test
    public void testCallableDemo1() {
        try {
            ExecutorService service = Executors.newFixedThreadPool(1);
            FutureTask<Integer> futureTask = new FutureTask(new Durant());
            Future future = service.submit(futureTask);
            Thread.sleep(1000);
            System.out.println(futureTask.get());
            System.out.println("done");
        } catch (Exception e) {
            System.out.println("testCallableDemo-error");
            e.printStackTrace();
        }
    }

    @Test
    public void testCallableDemo2() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        FutureTask<Integer> futureTask = new FutureTask(new Durant());
        futureTask.run();
    }

}
