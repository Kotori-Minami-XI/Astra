package com.Kotori.Playground.DesignMode;

import org.junit.Test;

public class StrategyMode {
    @Test
    public void testStrategyMode() {
        Traveler traveler = new Traveler();
        traveler.setTravelStrategy(new PlaneStrategy());
        traveler.travel();
    }
}

//抽象的策略
interface TravelStrategy {
    public String SelectStrategy();
}

//火车出行的策略
class TrainStrategy implements TravelStrategy {
    @Override
    public String SelectStrategy() {
        return "火车出行";
    }
}

//飞机出行的策略
class PlaneStrategy implements TravelStrategy {
    @Override
    public String SelectStrategy() {
        return "飞机出行";
    }
}

//汽车出行的策略
class CarStrategy implements TravelStrategy {
    @Override
    public String SelectStrategy() {
        return "汽车出行";
    }
}

//环境类 此处是旅行者
class Traveler {
    private TravelStrategy travelStrategy;

    public void setTravelStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public void travel() {
        System.out.println(travelStrategy.SelectStrategy());
    }

}