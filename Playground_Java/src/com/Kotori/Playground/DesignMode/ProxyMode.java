package com.Kotori.Playground.DesignMode;

import org.junit.Test;

interface HouseService {
    void buyHouse();
}

class HouseServiceImpl implements HouseService {
    public void buyHouse() {
        System.out.println("Buy house");
    }
}

class HouseServiceProxy implements HouseService{
    private HouseService houseService;

    public HouseServiceProxy(HouseService houseService) {
        this.houseService = houseService;
    }

    @Override
    public void buyHouse() {
        System.out.println("Before Buy house");
        houseService.buyHouse();
        System.out.println("After Buy house");
    }
}

public class ProxyMode {
    public static void main(String[] args) {
        HouseServiceProxy proxy = new HouseServiceProxy(new HouseServiceImpl());
        proxy.buyHouse();
    }
}

