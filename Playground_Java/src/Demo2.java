import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo2 {
    @Test
    public void test2() {
        Cat lion = new Lion();

        Cat proxyInstance = (Cat)Proxy.newProxyInstance(lion.getClass().getClassLoader(), lion.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("Lion row!");
                return method.invoke(lion, objects);
            }
        });
        String s = proxyInstance.eat();
        System.out.println(s);
    }
}

interface Cat {
    public String eat();
}

class Lion implements Cat {
    @Override
    public String eat() {
        System.out.println("Lion eat!!!!");
        return "Lion eat!!!!";
    }
}
