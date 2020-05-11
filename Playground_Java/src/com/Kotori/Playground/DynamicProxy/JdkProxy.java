package com.Kotori.Playground.DynamicProxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {
    @Test
    public void testJdkProxy() {
        IUser user = new UserImpl();

        // 创建JDK代理对象
        IUser proxyInstance = (IUser)Proxy.newProxyInstance(
                user.getClass().getClassLoader(), user.getClass().getInterfaces(), new LogHandler(user));

        proxyInstance.doSomething();
    }
}

// 原始的接口
interface IUser {
    public void doSomething();
}

// 原始接口的实现类
class UserImpl implements IUser {
    public void doSomething() {
        System.out.println("TargetClass is doing something");
    }
}

class LogHandler implements InvocationHandler {
    private Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        proxy = method.invoke(target, objects); //执行方法并获取代理对象
        this.printLog();
        return proxy;
    }

    private void printLog() {
        System.out.println("Print log");
    }
}
