package com.Kotori.Scene.MatchingGame.Algorithm;

import com.Kotori.Scene.MatchingGame.Entity.Player;
import org.apache.commons.math3.analysis.function.Sqrt;

public class ClassicEuclideanAlgorithm implements MatchingAlgorithm{
    @Override
    public double calculateDistance(Player player1, Player player2) {
        Integer numOfItems = player1.getPreference().getNumOfItems();

        double sum = 0;
        for (int i = 0; i < numOfItems; i++) {
            double diff = player1.getPreference().getItem(i) - player2.getPreference().getItem(i);
            sum += diff * diff;
        }

        return new Sqrt().value(sum);
    }
}
