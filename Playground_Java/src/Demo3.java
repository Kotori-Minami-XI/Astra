import org.junit.Test;

public class Demo3 {
    Child child = new Child();

    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (Parent.class) {
                        child.num++;
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(child.num);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (Parent.class) {
                        child.num++;
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(child.num);
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}

class Parent {

}

class Child extends Parent{
    int num =0;
}


