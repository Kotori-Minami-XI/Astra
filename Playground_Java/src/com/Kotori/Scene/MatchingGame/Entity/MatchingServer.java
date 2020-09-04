package com.Kotori.Scene.MatchingGame.Entity;

import com.Kotori.Scene.MatchingGame.Algorithm.MatchingAlgorithm;

public class MatchingServer implements Runnable {
    private MatchingAlgorithm matchingAlgorithm;

    public MatchingServer(MatchingAlgorithm matchingAlgorithm) {
        this.matchingAlgorithm = matchingAlgorithm;
    }

    public double getDistance (Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new RuntimeException("对象不能为空！");
        }

        Integer numOfItems = player1.getPreference().getNumOfItems();
        if (numOfItems != player2.getPreference().getNumOfItems()) {
            throw new RuntimeException("两个对象长度不一致！");
        }
        
        return matchingAlgorithm.calculateDistance(player1, player2);
    }

    @Override
    public void run() {

    }
}
