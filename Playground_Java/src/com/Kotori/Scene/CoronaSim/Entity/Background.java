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

    public void doStatistic() {
        if (null == background) {
            throw new RuntimeException("请先初始化background");
        }

        int numOfTotalPerson = 0;
        int numOfInfectedPerson = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point point = pointCollection[i][j];
                for (Person person : point.getPersonSet()) {
                    numOfTotalPerson++;
                    if (person.isisInfected()) {
                        numOfInfectedPerson++;
                    }
                }
            }
        }

        System.out.println("总人数为"+numOfTotalPerson);
        System.out.println("感染人数为"+numOfInfectedPerson);
        System.out.println("感染率为" + numOfInfectedPerson * 1.0 / numOfTotalPerson);
    }


}
