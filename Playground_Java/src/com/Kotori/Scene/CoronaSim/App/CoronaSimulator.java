package com.Kotori.Scene.CoronaSim.App;

import com.Kotori.Scene.CoronaSim.Entity.Background;
import com.Kotori.Scene.CoronaSim.Entity.Person;
import org.junit.Test;

public class CoronaSimulator {

    @Test
    public void testMove() {
        Background.InitBackground(100,100);
        Person person = new Person(0, "person0");
        System.out.println(person);
        person.randomMove(1);
        System.out.println(person);
        person.randomMove(1);
        System.out.println(person);
    }

}
