import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;


class A {
    public void f() {
        System.out.println("f");
    }
}

public class Demo1 {

    @Test
    public void test() throws InterruptedException, IllegalAccessException, InstantiationException {
        Class clazz = A.class;
        A a = (A) clazz.newInstance();
        a.f();
    }



}






