package com.Kotori.Playground.Thread;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SynchronizedDemo {
    @Test
    public void test() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(new BulletMaker("BulletMaker1")));

        threads.add(new Thread(new Gun("Gun1")));
        threads.add(new Thread(new Gun("Gun2")));

        for (Thread Thread : threads) {
            Thread.start();
        }

        for (Thread Thread : threads) {
            Thread.join();
        }
    }
}

class Bullet {
    static volatile Integer bullets = 5;

    //放在静态方法上，锁的是当前的class，此处的锁和synchronized(Bullet.class)是同样的
    public static synchronized void consumeBullets1(String name) {
        if (Bullet.bullets > 0) {
            Bullet.bullets--;
            System.out.println(name + "消耗了子弹，还剩下" + Bullet.bullets);
        }
    }

    //放在静态方法上，锁的是当前的class，此处的锁和synchronized(Bullet.class)是同样的
    public static synchronized void produceBullets1(String name) {
        if (Bullet.bullets < 20) {
            Bullet.bullets++;
            System.out.println(name + "生产了子弹，还剩下" + Bullet.bullets);
        }
    }

    //锁的是当前的class
    public static void consumeBullets2(String name) {
        synchronized (Bullet.class) {
            if (Bullet.bullets > 0) {
                Bullet.bullets--;
                System.out.println(name + "消耗了子弹，还剩下" + Bullet.bullets);
            }
        }
    }

    //锁的是当前的class
    public static void produceBullets2(String name) {
        synchronized (Bullet.class) {
            if (Bullet.bullets < 20) {
                Bullet.bullets++;
                System.out.println(name + "生产了子弹，还剩下" + Bullet.bullets);
            }
        }
    }
}

class Gun implements Runnable {
    private String name;

    public Gun(String name) {
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
                Bullet.consumeBullets2(this.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BulletMaker implements Runnable {
    private String name;

    public BulletMaker(String name) {
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
                Bullet.produceBullets1(this.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}