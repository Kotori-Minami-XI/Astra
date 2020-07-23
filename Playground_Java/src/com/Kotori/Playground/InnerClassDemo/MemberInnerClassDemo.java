package com.Kotori.Playground.InnerClassDemo;

import org.junit.Test;

public class MemberInnerClassDemo {
    @Test
    public void test1() {
        Circle circle = new Circle(0.0);
        circle.useDraw();
    }

    /***
     * 成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。
     */
    @Test
    public void test2() {
        Circle circle = new Circle(0.0);
        Circle.Draw draw = circle.new Draw();
        draw.drawShape();
    }
}

/***
 * 类Draw像是类Circle的一个成员，Circle称为外部类。
 * 成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）。
 *
 */
class Circle {
    double radius = 0;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void useDraw() {
        Draw draw = new Draw();
        draw.drawShape();
    }

    class Draw {     //成员内部类
        double radius = 1.0;

        public void drawShape() {
            System.out.println("draw-drawShape()");
            System.out.println("内部成员radius= "+ Draw.this.radius);
            System.out.println("外部成员radius= "+ Circle.this.radius);
        }
    }
}
