package com.Kotori.Scene.ConsumerAndProducerScene;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.Kotori.Scene.ConsumerAndProducerScene.FixedThreadPoolMethod.tasks;

public class FixedThreadPoolMethod {
    static BlockingQueue<Iron> tasks = new LinkedBlockingQueue();

    /***
     * @brief  利用ThreadPool实现生产着消费者模型
     * @throws IOException
     */
    @Test
    public void testFixedThreadPoolMethod() throws IOException {
        int consumerNum = 4;
        int ironNum = 30;

        for (int i = 0; i < ironNum; i++) {
            tasks.offer(new Iron("Iron" + i));
        }

        ExecutorService service = Executors.newFixedThreadPool(consumerNum);
        List<IronConsumer> consumerList = new ArrayList();
        for (int i = 0; i < consumerNum; i++) {
            consumerList.add(new IronConsumer("IronConsumer" + i));
        }

        while (tasks.size() > 0) {
            for (int i = 0; i < consumerNum; i++) {
                service.execute(consumerList.get(i));
            }
        }
        System.in.read();
    }

    @Test
    public void testThreadPoolExecutorMethod() throws IOException {
        int consumerNum = 4;
        int ironNum = 30;

        for (int i = 0; i < ironNum; i++) {
            tasks.offer(new Iron("Iron" + i));
        }

        ThreadPoolExecutor service = new ThreadPoolExecutor(
                consumerNum, consumerNum, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

        List<IronConsumer> consumerList = new ArrayList();
        for (int i = 0; i < consumerNum; i++) {
            consumerList.add(new IronConsumer("IronConsumer" + i));
        }

        while (tasks.size() > 0) {
            for (int i = 0; i < consumerNum; i++) {
                service.execute(consumerList.get(i));
            }
        }
        System.in.read();
    }

    @Test
    public void testThreadPoolExecutorReject() throws IOException {
        int consumerNum = 4;
        int ironNum = 30;

        for (int i = 0; i < ironNum; i++) {
            tasks.offer(new Iron("Iron" + i));
        }


        class myPolicy implements RejectedExecutionHandler {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                System.out.println("myPolicy!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }

        ThreadPoolExecutor service = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(5),
                new myPolicy());

        List<IronConsumer> consumerList = new ArrayList();
        for (int i = 0; i < consumerNum; i++) {
            consumerList.add(new IronConsumer("IronConsumer" + i));
        }

        while (tasks.size() > 0) {
            for (int i = 0; i < consumerNum; i++) {
                service.execute(consumerList.get(i));
            }
        }
        System.in.read();
    }
}

class Iron {
    private String name;

    public Iron(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class IronProducer implements Runnable {
    private String name;

    public IronProducer(String name) {
        this.name = name;
    }

    @Override
    public void run() {

    }
}

class IronConsumer implements Runnable {
    private String name;

    public IronConsumer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Iron iron = tasks.take();
            Thread.sleep(1000);
            System.out.println(this.name + "消费了" + iron.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

