package com.Kotori.Playground.classloaderDemo;

import org.junit.Test;

import java.io.*;

public class ClassLoaderDemo {
    @Test
    public void testClassLoaderDemo() {
        String path = "C:\\Users\\xz\\Desktop\\Java\\Astra\\target\\classes\\com\\Kotori\\Playground\\classloaderDemo\\";
        MyClassLoader myClassLoader = new MyClassLoader(path);
        try {
            Class clazz = myClassLoader.findClass("HelloWorld");
            System.out.println(clazz);
            System.out.println(clazz.getConstructor().newInstance());
            System.out.println("--------------------1. testClassLoaderDemo done--------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTwoClassLoader() {
        String path = "C:\\Users\\xz\\Desktop\\Java\\Astra\\target\\classes\\com\\Kotori\\Playground\\classloaderDemo\\";
        MyClassLoader myClassLoader1 = new MyClassLoader(path);
        MyClassLoader myClassLoader2 = new MyClassLoader(path);
        try {

            /*
             * 1. clazz1 和 clazz2 不相等， 他们通过不同的classloader装载
             *    不能够重复用一个myClassLoader去加载同一个类，会报错attempted duplicate class definition
             */
            Class clazz1 = myClassLoader1.findClass("HelloWorld");
            Class clazz2 = myClassLoader2.findClass("HelloWorld");
            System.out.println(clazz1.equals(clazz2));
            System.out.println(clazz2 == clazz1);
            System.out.println("----------------------------------------");

            /*
             * 2. clazz1 和 clazz3 不相等， 他们通过同一个classloader装载，但是字节码并不相同
             */
            Class clazz3 = myClassLoader1.findClass("ByeWorld");
            System.out.println(clazz1.equals(clazz3));
            System.out.println(clazz3 == clazz1);

            System.out.println("--------------------2. testTwoClassLoader done--------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyClassLoader extends ClassLoader {
    private String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = path+name+".class";
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(classPath);
            outputStream = new ByteArrayOutputStream();
            int temp = 0;
            while((temp = inputStream.read()) != -1){
                outputStream.write(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] bytes = outputStream.toByteArray();

        return defineClass(null,bytes,0,bytes.length);
    }
}


