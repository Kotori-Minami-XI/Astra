package com.Kotori.Playground.DesignMode;

import org.junit.Test;

interface Shoe {
    public void getShoe();
}

class Nike implements Shoe {
    @Override
    public void getShoe() {
        System.out.println("This is Nike shoe");
    }
}

class Jordan implements Shoe {
    @Override
    public void getShoe() {
        System.out.println("This is Jordan shoe");
    }
}

interface ShoeFactory {
    public Shoe makeShoe();
}

class NikeFactory implements ShoeFactory {
    @Override
    public Shoe makeShoe() {
        return new Nike();
    }
}

class JordanFactory implements ShoeFactory {
    @Override
    public Shoe makeShoe() {
        return new Jordan();
    }
}

public class FactoryMode {
    @Test
    public void testFactoryMode() {
        ShoeFactory factory = new JordanFactory();
        factory.makeShoe().getShoe();
    }
}
