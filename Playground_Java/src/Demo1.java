import org.junit.Test;



class Counter{
    public Integer count;

    public Counter() {
        count = 0;
    }
}

public class Demo1 {
    @Test
    public void test() throws InterruptedException {
        Counter counter1 =new Counter();
        Counter counter2 =new Counter();

        Thread t1 = new Thread(new Adder(counter1, "t1"));
        Thread t2 = new Thread(new Adder(counter2,"t2"));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}


class Adder implements Runnable{
    private String name;
    private Counter counter;

    public Adder(Counter counter, String name) {
        this.counter = counter;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Counter.class) {
                try {
                    this.counter.count++;
                    System.out.println(this.name + " count=" + this.counter.count);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


