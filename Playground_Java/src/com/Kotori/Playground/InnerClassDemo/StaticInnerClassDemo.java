package com.Kotori.Playground.InnerClassDemo;

import org.junit.Test;

public class StaticInnerClassDemo {
    @Test
    public void test1() {

    }
}

/***
 * 静态内部类也是定义在另一个类里面的类，只不过在类的前面多了一个关键字static。
 * 静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，
 * 并且它不能使用外部类的非static成员变量或者方法，这点很好理解，因为在没有外部类的对象的情况下，可以创建静态内部类的对象，
 * 如果允许访问外部类的非static成员就会产生矛盾，因为外部类的非static成员必须依附于具体的对象。
 */
class Outter {
    public Integer a;
    public static Integer b;

    public Outter() {
        a = 1;
        b = 2;
    }

    static class Inner {
        public Inner() {
            //System.out.println(a); //不能访问外部类非静态的成员或者方法
            System.out.println(b);   //能访问外部类静态的成员或者方法
        }
    }
}

