package com.Kotori.Playground.Reference;

import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class WeakReference {
    private <T1,T2> void printWeakHashMap(WeakHashMap<T1,T2> map){
        Set<Map.Entry<T1,T2>> entrySet = map.entrySet();
        for (Map.Entry<T1,T2> entry: entrySet){
            System.out.println(entry.getKey() + "-"+ entry.getValue());
        }
    }

    /***
     * 测试弱引用
     * 无论内存是否足够，只要 JVM 开始进行垃圾回收，那些被弱引用关联的对象都会被回收
     */
    @Test
    public void testWeakReference() {
        java.lang.ref.WeakReference<byte[]> sr = new java.lang.ref.WeakReference<>(new byte[1024]);
        System.out.println(sr.get()); //弱引用还存在
        System.gc(); //主动通知垃圾回收
        System.out.println(sr.get()); //弱引用已经被清空
    }

    /***
     * 测试WeakHashMap
     * 会被回收因为 map 的 key 用 new String 实例化了一个对象
     * 保存在堆里,虽然是线程共享,但是并没有任何引用指向这个key
     */
    @Test
    public void testWeakHashMap1() {
        WeakHashMap<String, String> weakHashMap = new WeakHashMap();

        // key存在于堆，被GC回收
        weakHashMap.put(new String("1"),new String("abc"));
        printWeakHashMap(weakHashMap); //weakHashMap中的对象还存在
        System.gc(); //主动通知垃圾回收
        printWeakHashMap(weakHashMap); //weakHashMap中的对象已经被清理
        System.out.println("----------------------------------------------------------------");

        weakHashMap.clear(); //清空map
        // key存在于常量区，不会被回收
        weakHashMap.put("2",new String("abc"));
        printWeakHashMap(weakHashMap); //weakHashMap中的对象还存在
        System.gc(); //主动通知垃圾回收
        printWeakHashMap(weakHashMap); //weakHashMap中的对象存在
        System.out.println("----------------------------------------------------------------");
    }

    /***
     * 测试WeakHashMap对对象的回收情况
     */
    @Test
    public void testWeakHashMap2() {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap();

        // key为Integer的时候 如果在-128---127的范围内则存在于方法区中的static池中，不会被GC回收
        weakHashMap.put(1, new String("ls"));
        printWeakHashMap(weakHashMap); //weakHashMap中的对象还存在
        System.gc(); //主动通知垃圾回收
        printWeakHashMap(weakHashMap); //weakHashMap中的对象依旧存在
        System.out.println("----------------------------------------------------------------");

        weakHashMap.clear(); //清空map
        // key为Integer的时候 如果在-128---127的范围内则存在于方法区中的static池中，不会被GC回收
        weakHashMap.put(200, new String("ls"));
        printWeakHashMap(weakHashMap); //weakHashMap中的对象还存在
        System.gc(); //主动通知垃圾回收
        printWeakHashMap(weakHashMap); //weakHashMap中的对象已经被清理
        System.out.println("----------------------------------------------------------------");
    }


}
