package com.Kotori.Scene.MatchingGame.Entity;

import java.util.Random;

public class Preference {
    private Integer numOfItems;
    private Double itemList[];

    public Preference(Integer numOfItems) {
        this.numOfItems = numOfItems;
        itemList = new Double[numOfItems];
    }

    public void generateRandomPreference() {
        Random random = new Random();
        for (int i = 0; i < numOfItems; i++) {
            itemList[i] = random.nextDouble();
        }
    }

    public Double getItem(int index) {
        return itemList[index];
    }

    public void setItem(int index, double val) {
        itemList[index] = val;
    }

    public Integer getNumOfItems() {
        return numOfItems;
    }
}
