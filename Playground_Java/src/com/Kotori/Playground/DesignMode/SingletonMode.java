package com.Kotori.Playground.DesignMode;

// 懒汉式
class Singleton_L {
    // 禁止外部调用构造函数
    private Singleton_L(){
    }

    // volatile禁止编译器重排序
    private static volatile Singleton_L instance = null;

    public Singleton_L getInstance(){
        // 双重检查锁，第一个判断满足性能要求，第二个判断是真正判断是否为空
        if (null == instance){
             synchronized (instance) {
                if (null == instance){
                    instance = new Singleton_L();
                }
            }
        }
        return instance;
    }
}

// 饿汉式
class Singleton_H {
    // 禁止外部调用构造函数
    private Singleton_H(){
    }

    private static Singleton_H instance = new Singleton_H();
    public Singleton_H getInstance(){
        return instance;
    }
}