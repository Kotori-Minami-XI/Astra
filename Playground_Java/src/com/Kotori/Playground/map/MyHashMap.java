package com.Kotori.Playground.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap {
    @Test
    public void testMyHashMap() {
        HashMap<String, Integer> map = new HashMap();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);

        for (String key : map.keySet()) {
            System.out.println(key + "---" + map.get(key));
        }

        System.out.println("-------------------------------------");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }
}
