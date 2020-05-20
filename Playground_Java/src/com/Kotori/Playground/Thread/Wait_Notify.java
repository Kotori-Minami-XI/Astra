package com.Kotori.Playground.Thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Wait_Notify {

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(new OilMaker("OilMaker1")));
        threads.add(new Thread(new OilMaker("OilMaker2")));

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
            synchronized (OilGoods.class) {
                while (OilGoods.Oil <= 0) {
                    try {
                        OilGoods.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                OilGoods.Oil--;
                System.out.println(name + "消耗了Oil，还剩下" + OilGoods.Oil);
                OilGoods.class.notifyAll();
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
        try{
            while (true) {
                synchronized (OilGoods.class) {
                    while (OilGoods.Oil >= 20) {
                        OilGoods.class.wait();
                    }
                    OilGoods.Oil++;
                    System.out.println(name + "生产了Oil，还剩下" + OilGoods.Oil);
                    OilGoods.class.notifyAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}