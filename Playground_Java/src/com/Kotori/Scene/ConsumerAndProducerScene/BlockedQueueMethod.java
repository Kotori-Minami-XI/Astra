package com.Kotori.Scene.ConsumerAndProducerScene;


import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/***
 *  直接采用LinkedBlockingQueue去承接cargo的任务，由consumer消耗LinkedBlockingQueue中的cargo
 */
public class BlockedQueueMethod {
    static BlockingQueue<Cargo> tasks = new LinkedBlockingQueue();
    @Test
    public void BlockedQueueMethod() throws IOException {
        int cargoNum =30;
        int consumerNum =4;


        for (int i=0; i < cargoNum; i++) {
            tasks.offer(new Cargo("Cargo"+i));
        }

        for (int i=0; i< consumerNum; i++) {
            new Thread(new CargoConsumer("CargoConsumer"+i)).start();
        }

        System.in.read();
    }

    class Cargo {
        private String name;

        public Cargo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class CargoProducer implements Runnable {
        private String name;

        public CargoProducer(String name) {
            this.name = name;
        }

        @Override
        public void run() {

        }
    }

    class CargoConsumer implements Runnable {
        private String name;

        public CargoConsumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Cargo cargo = null;
                while ((cargo = BlockedQueueMethod.tasks.take()) != null) {
                    Thread.sleep(1000);
                    System.out.println(this.name + "消费了" + cargo.getName());//print might incorrect
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
