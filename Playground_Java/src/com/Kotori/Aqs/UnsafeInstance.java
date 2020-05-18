package com.Kotori.Aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeInstance {
    public static Unsafe getUnsafeInstance() {
        try {
            // 反射获取Unsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
