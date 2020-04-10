package com.Kotori.Playground.Reference;


import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;

public class FourReference {
    /***
     * 获取目前JVM环境的参数
     */
    @Test
    public void testPerformance(){
        System.out.println("最大可用内存，对应-Xmx:" + Runtime.getRuntime().maxMemory());
        System.out.println("当前JVM空闲内存:" + Runtime.getRuntime().freeMemory());
        System.out.println("当前JVM占用的内存总数，其值相当于当前JVM已使用的内存及freeMemory()的总和:"
                + Runtime.getRuntime().totalMemory());

    }

    /***
     * 测试强引用,先设置-Xmx4m -Xms4m
     * 当内存不够使用，程序直接报错，强引用并不会被回收
     */
    @Test
    public void testStrongRef(){
        byte[] buff = new byte[1024 * 1024 * 1];
        buff = new byte[1024 * 1024 * 1];
    }

    /***
     * 测试软引用,先设置-Xmx4m -Xms4m
     * 当内存不够使用，程序会清理软引用的对象
     */
    @Test
    public void testSoftRef(){
        List<SoftReference> list = new ArrayList();
        for (int i = 0; i < 10 ; i++){
            SoftReference<byte[]> softRef = new SoftReference(new byte[1024 *  1024 * 1]);
            list.add(softRef);
        }

        for(int i=0; i < list.size(); i++){
            Object obj = ((SoftReference) list.get(i)).get();
            System.out.println(obj);
        }
    }

}
