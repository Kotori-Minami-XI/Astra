import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class Demo1 {

    @Test
    public void test() throws InterruptedException {
        List<Thread> list = new ArrayList<>();

        //Module module = new Module();
        for (int i=0;i<100;i++) {
            list.add(new Thread(new Module()));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }

        //System.out.println(module.state);
    }

}




class A{

}


class Module extends A implements Runnable{
    //public volatile Integer state = 0;
    public static Integer state = 0;

    @Override
    public void run() {
        synchronized (Module.state) {
            Module.state ++;
            System.out.println("模组启动了" + Module.state);
        }

//        if (this.state == 0) {
//            this.state ++;
//            System.out.println("模组启动了" + this.state);
//        } else {
//            System.out.println("错误！模组已经启动" + this.state);
//        }
    }
}






