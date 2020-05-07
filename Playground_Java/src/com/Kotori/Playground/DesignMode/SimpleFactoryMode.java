package com.Kotori.Playground.DesignMode;

import org.junit.Test;

interface Shape {
    public void shape();
}

class Circle implements Shape {
    @Override
    public void shape() {
        System.out.println("This is circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void shape() {
        System.out.println("This is Rectangle");
    }
}

class Triangle implements Shape {
    @Override
    public void shape() {
        System.out.println("This is Triangle");
    }
}

class ShapeFactory {
    public static Shape createShape(String name) {
        if ("Triangle".equals(name)) {
            return new Triangle();
        } else if ("Rectangle".equals(name)) {
            return new Rectangle();
        } else if (("Circle".equals(name))) {
            return new Circle();
        } else {
            return null;
        }
    }
}

public class SimpleFactoryMode {
    @Test
    public void testSimpleFactoryMode() {
        Shape shape = ShapeFactory.createShape("Circle");
        shape.shape();
    }

}

