package com.Kotori.Playground.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Goods{
    static Lock goodsLock = new ReentrantLock();
    static Integer goodsNum = 0;
    static Integer goodsMax = 20;
}

class Producer implements Runnable{
    String name;
    Integer sleepTime;
    Producer(Integer id, Integer Interval){
        name = "生产者" + id.toString();
        sleepTime = Interval;
    }
    @Override
    public void run() {
        while(true){
            try {
                if (Goods.goodsLock.tryLock()) {
                    if (Goods.goodsNum < Goods.goodsMax){
                        Goods.goodsNum++;
                        System.out.println(
                                name.toString() + "生产了一个goods,目前总共有goods：" + Goods.goodsNum.toString());
                    }
                    Goods.goodsLock.unlock();
                }
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Goods.goodsLock.unlock();
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    String name;
    Integer sleepTime;
    Consumer(Integer id, Integer Interval){
        name = "消费者" + id.toString();
        sleepTime = Interval;
    }
    @Override
    public void run() {
        while(true){
            try {
                if (Goods.goodsLock.tryLock()) {
                    if (Goods.goodsNum > 0){
                        Goods.goodsNum--;
                        System.out.println(
                                name.toString() + "消费了一个goods,目前总共有goods：" + Goods.goodsNum.toString());
                    }
                    Goods.goodsLock.unlock();
                }
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Goods.goodsLock.unlock();
                e.printStackTrace();
            }
        }
    }
}

public class Producer_Consumer {
    public static void main(String[] args) {
        int producerNum = 2;
        int consumerNum = 1;

        List<Thread> threadPool = new ArrayList<Thread>();
        for (int i = 1; i <= producerNum; i++){
            threadPool.add(new Thread(new Producer(i,500)));
        }
        for (int i = 1; i <= consumerNum; i++){
            threadPool.add(new Thread(new Consumer(i,1000)));
        }

        for (Thread t : threadPool){
            t.start();
        }
    }
}
