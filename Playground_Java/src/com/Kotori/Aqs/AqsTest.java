package com.Kotori.Aqs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AqsTest {

    @Test
    public void test() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        threads.add(new Thread(new Eater("Eater1")));
        threads.add(new Thread(new Eater("Eater2")));

        threads.add(new Thread(new Cook("Cook1")));
        //threads.add(new Thread(new Cook("Cook2")));

        for (Thread Thread : threads) {
            Thread.start();
        }

        for (Thread Thread : threads) {
            Thread.join();
        }
    }
}


class Food {
    static volatile Integer foodNum = 5;
    public void consumeFood() {

    }
}

class Cook implements Runnable {
    private String name;

    public Cook (String name){
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
                if (Food.foodNum < 20) {
                    Food.foodNum++;
                    System.out.println(this.getName() + "生产了一份Food，现在有" + Food.foodNum + "份食物");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Eater implements Runnable {
    private String name;

    public Eater (String name){
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
                if (Food.foodNum > 0) {
                    Food.foodNum--;
                    System.out.println(this.getName() + "消耗了一份Food，现在有" + Food.foodNum + "份食物");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
