package com.Kotori.Playground.Thread;


import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPool {
    @Test
    public void testThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 6,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i <= 10; i++)
        {
            Task task = new Task("Task " + i);
            System.out.println("A new task has been added : " + task.getName());
            executor.execute(task);
        }

        executor.shutdown();
        while(true) {}
    }
}

class Task implements Runnable
{
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try
        {
            Long duration = (long) (Math.random() * 10);
            System.out.println("Doing a task during : " + name);
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
