package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Wait_Notify {

    @Test
    public void test() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(new OilMaker("OilMaker1")));

        threads.add(new Thread(new Car("Car1")));
        threads.add(new Thread(new Car("Car2")));

        for (Thread Thread : threads) {
            Thread.start();
        }

        for (Thread Thread : threads) {
            Thread.join();
        }
    }
}

class OilGoods {
    static volatile Integer Oil = 5;

    public void consume(String name) throws InterruptedException {
        while (OilGoods.Oil <= 0) {
            wait();
        }
        OilGoods.Oil++;
        System.out.println(name + "消耗了Oil，还剩下" + OilGoods.Oil);
        notifyAll();
    }

    public void produce(String name) throws InterruptedException {
        while (OilGoods.Oil >= 20) {
            wait();
        }
        OilGoods.Oil++;
        System.out.println(name + "生产了Oil，还剩下" + OilGoods.Oil);
        notifyAll();
    }

}

class Car implements Runnable {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                OilGoods.consume(this.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class OilMaker implements Runnable {
    private String name;

    public OilMaker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                OilGoods.produce(this.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}