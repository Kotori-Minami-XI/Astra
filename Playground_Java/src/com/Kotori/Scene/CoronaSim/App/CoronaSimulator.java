package com.Kotori.Scene.CoronaSim.App;

import com.Kotori.Scene.CoronaSim.Entity.Background;
import com.Kotori.Scene.CoronaSim.Entity.Person;
import org.junit.Test;

public class CoronaSimulator {

    @Test
    public void testMove() {
        Background.initBackground(100,100);

        for (int i = 0; i < 100; i++) {
            Person person = new Person(i, "person" + i);
        }
    }

}
