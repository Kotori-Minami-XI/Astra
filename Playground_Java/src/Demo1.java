import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;


public class Demo1 {

    @Test
    public void test() throws InterruptedException {
        List<Thread> list = new ArrayList<>();

        for (int i=0;i<1;i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread");
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }
    }

    @Test
    public void test2() {

    }




}






