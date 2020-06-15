package com.Kotori.HotDeployment;

import org.junit.Test;

import java.lang.reflect.Method;

public class TestSaleDay {
    @Test
    public void testSaleDay() throws Exception {
        String path = "target/classes/com/Kotori/HotDeployment/";

        while (true) {
            SaleDayClassLoader classLoader = new SaleDayClassLoader(path);
            Class clazz = classLoader.findClass("SaleDay");

            Object instance = clazz.getConstructor().newInstance();
            Method method = clazz.getMethod("changeInfo") ;
            method.invoke(instance) ;
            Thread.sleep(3000);
        }
    }
}
