package com.Kotori.Scene.CoronaSim.Entity;

import java.util.HashSet;
import java.util.Set;

public class Point {
    private Integer coordinateX;
    private Integer coordinateY;
    private Set<Person> personSet;

    public Point(Integer coordinateX, Integer coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        personSet = new HashSet();
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    public Integer getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Integer coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Integer getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Integer coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void savePerson(Person person) {
        this.personSet.add(person);
    }

    public void removePerson(Person person) {
        this.personSet.remove(person);
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", personSet=" + personSet +
                '}';
    }
}
