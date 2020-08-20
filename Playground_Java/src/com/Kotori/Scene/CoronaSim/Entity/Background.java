package com.Kotori.Scene.CoronaSim.Entity;

public class Background {
    static Integer width;
    static Integer height;
    static Point pointCollection[][];
    public static Background background;

    public static Background initBackground(Integer width, Integer height) {
        if (background == null) {
            background = new Background(width, height);
        }

        for (Integer i = 0; i < height; i++) {
            for (Integer j = 0; j < width; j++) {
                pointCollection[i][j] = new Point(i, j);
            }
        }

        return background;
    }

    private Background(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        pointCollection = new Point[height][width];
    }
}
