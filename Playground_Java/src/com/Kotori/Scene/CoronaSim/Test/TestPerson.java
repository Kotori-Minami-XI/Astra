package com.Kotori.Scene.CoronaSim.Test;

import com.Kotori.Scene.CoronaSim.Entity.Background;
import com.Kotori.Scene.CoronaSim.Entity.Person;
import org.junit.Test;

public class TestPerson {
    @Test
    public void testPersonMove() {
        Background.initBackground(10,10);
        Person person = new Person(0,"kotori");

        for (int i = 0; i < 100; i++) {
            System.out.println(person.getPersonLocationPoint());
            person.randomMove(1);
        }
    }
}
