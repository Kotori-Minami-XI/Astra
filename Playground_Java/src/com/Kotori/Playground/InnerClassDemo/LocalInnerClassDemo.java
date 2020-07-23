package com.Kotori.Playground.InnerClassDemo;

import org.junit.Test;

public class LocalInnerClassDemo {
    @Test
    public void test1() {

    }
}

class People{
    public People() {
    }
}

/***
 * 局部内部类是定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内。
 */
class Man{
    public Man(){
    }

    /***
     * 局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的。
     */
    public People getWoman(){
        class Woman extends People{   //局部内部类
            int age =0;
        }
        return new Woman();
    }
}
