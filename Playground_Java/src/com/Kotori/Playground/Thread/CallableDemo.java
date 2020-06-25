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

class Curry implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Running Curry");
        return "Curry";
    }
}

class Toms implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Running Toms");
        throw new Exception("Toms error");
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
    public void testCallableDemo2() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> future = service.submit(new Curry());
        System.out.println(future.get());
    }

    @Test
    public void testCallableDemo3() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(new Toms());

        try {
            futureTask.run();
            System.out.println(futureTask.get());
        } catch (Exception e) {
            System.out.println("error---------------------------------");
            e.printStackTrace();
        }


    }

}
