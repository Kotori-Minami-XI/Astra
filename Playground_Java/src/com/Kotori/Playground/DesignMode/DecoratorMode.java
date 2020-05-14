package com.Kotori.Playground.DesignMode;

import org.junit.Test;

public class DecoratorMode {
    @Test
    public void testDecoratorMode() {
        AbstractCoffee abstractCoffee = new Coffee();
        abstractCoffee = new SugarDecorator(abstractCoffee);
        abstractCoffee = new MilkDecorator(abstractCoffee);
        abstractCoffee = new SugarDecorator(abstractCoffee);

        System.out.println(abstractCoffee.getDesc());
        System.out.println(abstractCoffee.cost());
    }
}

//抽象的咖啡
interface AbstractCoffee {
    public abstract String getDesc();
    public abstract int cost();
}

//咖啡实体
class Coffee implements AbstractCoffee {
    @Override
    public String getDesc() {
        return "一杯咖啡";
    }

    @Override
    public int cost() {
        return 8;
    }
}

//咖啡的装饰类（父装饰类）
class AbstractCoffeeDecorator implements AbstractCoffee {
    private AbstractCoffee abstractCoffee;

    public AbstractCoffeeDecorator(AbstractCoffee abstractCoffee) {
        this.abstractCoffee = abstractCoffee;
    }

    @Override
    public String getDesc() {
        return this.abstractCoffee.getDesc();
    }

    @Override
    public int cost() {
        return this.abstractCoffee.cost();
    }
}

//糖的装饰类
class SugarDecorator extends AbstractCoffeeDecorator{
    public SugarDecorator(AbstractCoffee abstractCoffee) {
        super(abstractCoffee);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " 加糖";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}

//奶的装饰类
class MilkDecorator extends AbstractCoffeeDecorator{
    public MilkDecorator(AbstractCoffee abstractCoffee) {
        super(abstractCoffee);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " 加奶";
    }

    @Override
    public int cost() {
        return super.cost() + 3;
    }
}

