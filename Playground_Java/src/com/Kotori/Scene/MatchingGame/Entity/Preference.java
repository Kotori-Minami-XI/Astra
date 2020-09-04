package com.Kotori.Scene.MatchingGame.Entity;

public class Preference {
    private Integer numOfItems;
    private Double itemList[];

    public Preference(Integer numOfItems) {
        this.numOfItems = numOfItems;
        itemList = new Double[numOfItems];
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
