import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {
    public static void main(String[] args) throws IOException {
        byte a = (byte)2;
        switch(a) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    @Test
    public void test1() {
        int a;
    }
}

interface I {
    public abstract void f();
    public int a = 5;
    public Integer b = 1;
}






