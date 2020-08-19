package com.Kotori.Scene.CoronaSim.Entity;

import java.util.Random;

public class Person {
    private Integer personId;
    private String personName;
    private Boolean isInfected;
    private Point personLocationPoint;

    public Person(Integer personId, String personName) {
        this.personId = personId;
        this.personName = personName;
        isInfected = false;
        // 随机生成在某个点上，并更新这个点的person集合
        personLocationPoint = personRandomSpawn(Background.width, Background.height);
        personLocationPoint.savePerson(this);
    }

    private Point personRandomSpawn(Integer maxWidth, Integer maxHeight) {
        Random random = new Random();
        return Background.pointCollection[random.nextInt(maxWidth)][random.nextInt(maxHeight)];
    }

    public void action(int distance, int range, int infectProbability) {
        randomMove(distance);
        infect(range, infectProbability);
    }

    private void randomMove(int distance) {
        Integer currentCoordinateX = personLocationPoint.getCoordinateX();
        Integer currentCoordinateY = personLocationPoint.getCoordinateY();

        // 移除当前Point信息
        personLocationPoint.removePerson(this);
        personLocationPoint = null;

        // 计算下一个Point的位置
        Random random = new Random();
        int orient = random.nextInt(9) + 1;
        switch (orient) {
            case 1:
                currentCoordinateX -= distance;
                currentCoordinateY += distance;
                break;
            case 2:
                currentCoordinateY += distance;
                break;
            case 3:
                currentCoordinateX += distance;
                currentCoordinateY += distance;
                break;
            case 4:
                currentCoordinateX -= distance;
                break;
            case 6:
                currentCoordinateX += distance;
                break;
            case 7:
                currentCoordinateX -= distance;
                currentCoordinateY -= distance;
                break;
            case 8:
                currentCoordinateY -= distance;
                break;
            case 9:
                currentCoordinateX += distance;
                currentCoordinateY -= distance;
                break;
            default:
                break;
        }

        if (currentCoordinateX < 0) {
            currentCoordinateX = 0;
        } else if (currentCoordinateX >= Background.width) {
            currentCoordinateX = Background.width - 1;
        }

        if (currentCoordinateY < 0) {
            currentCoordinateY = 0;
        } else if (currentCoordinateY >= Background.height) {
            currentCoordinateY = Background.height - 1;
        }

        // 更新Point信息
        personLocationPoint = Background.pointCollection[currentCoordinateX][currentCoordinateY];
        Background.pointCollection[currentCoordinateX][currentCoordinateY].savePerson(this);
    }

    private void infect(int range, int infectProbability) {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return personId.equals(person.personId);
    }

    @Override
    public int hashCode() {
        return personId.hashCode();
    }
}
