package com.Kotori.Playground.Reflection;


import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;


public class Reflect {
    class Person {
        private String name;
        private String id;
        private Integer age;
        public String home;

        public String getMsg1(){
            return "收到了msg1:";
        }

        public String getMsg1(String msg){
            return "收到了msg1:" + msg;
        }

        public String getMsg2(String a){
            return "收到了msg2:";
        }

        public String getMsg3(Integer a, String b){
            return "收到了msg3:";
        }
    }

    Person person = new Person();
    Class clazz = person.getClass();

    /***
     *  获取成员变量
     */
    @Test
    public void test1(){
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields){
            System.out.println(f.getName());
        }
    }

    /***
     *  获取成员函数
     */
    @Test
    public void test2() throws InvocationTargetException, IllegalAccessException {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods){
            Class returnType = m.getReturnType();
            Parameter[] paras = m.getParameters();
            Class[] paraTypes = m.getParameterTypes();

            System.out.printf("成员函数：%s " + "函数返回类型：%s \n",
                    m.getName(),
                    returnType.getName());
            System.out.printf("参数类型："+ Arrays.toString(paraTypes)+"\n");
            System.out.printf("-------------------------------------------\n");
        }
    }

    /***
     *  获取特定的成员函数
     */
    @Test
    public void test3() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method m1 = clazz.getMethod("getMsg1");
        Class returnType = m1.getReturnType();
        Object obj = m1.invoke(person);
        System.out.println((String)obj);
    }

}
