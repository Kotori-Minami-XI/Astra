package com.Kotori.Playground.DynamicProxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


// 目标对象
class UserDao {
    public void doSomething () {
        System.out.println("doSomething");
    }
}

// 目标对象的代理
public class CglibProxy implements MethodInterceptor {
    @Test
    public void testCglibProxy() {
        UserDao userDao = new UserDao();
        CglibProxy userDaoCglibProxy = new CglibProxy();
        UserDao proxy = (UserDao) userDaoCglibProxy.createCglib(userDao);
        proxy.doSomething();
    }


    public <T> T createCglib (T target) {
        Enhancer enhancer = new Enhancer();
        // 指定目标对象的父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调
        enhancer.setCallback(this);
        // 创建子类对象并返回
        return (T) enhancer.create();
    }

    /***
     *
     * @param o 子类对象，即生成的代理对象
     * @param method 调用的父类方法
     * @param objects 调用的父类方法的参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("权限----------------");
        methodProxy.invokeSuper(o, objects);
        System.out.println("日志----------------");
        return o;
    }
}
