package com.Kotori.Playground.DesignMode;

public class TemplateMode {
    public static void main(String[] args) {
        DishTemplate dish1 = new Pizza();
        dish1.doDish();
        DishTemplate dish2 = new Burger();
        dish2.doDish();
    }
}

abstract class DishTemplate {
    public void doDish() {
        prepareDish();
        cookDish();
        serveDish();
    }

    abstract protected void prepareDish();

    abstract protected void cookDish();

    abstract protected void serveDish();
}

class Pizza extends DishTemplate {
    @Override
    protected void prepareDish() {
        System.out.println(" preparePizza");
    }

    @Override
    protected void cookDish() {
        System.out.println("cook Pizza");
    }

    @Override
    protected void serveDish() {
        System.out.println("serve Pizza");
    }
}

class Burger extends DishTemplate {
    @Override
    protected void prepareDish() {
        System.out.println("prepare Burger");
    }

    @Override
    protected void cookDish() {
        System.out.println("cook Burger");
    }

    @Override
    protected void serveDish() {
        System.out.println("serve Burger");
    }
}