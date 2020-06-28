package com.Kotori.Playground.collection.Set;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

public class MyTreeSet {
    //内部类
    class Student implements Comparable {
        String name;
        Integer age;

        @Override
        public int compareTo(Object obj) {
            Student stu = (Student) obj;
            if (stu.age == this.age && stu.name == this.name)
                return 0;
            if (stu.age >= this.age)
                return -1;
            else
                return 1;
        }

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    /***
     * 测试去重情况
     * TreeSet只需要继承Comparable并覆盖里面的compareTo就可以实现去重
     */
    @Test
    public void testTreeSet(){
        Set<Student> set = new TreeSet<Student>();
        set.add(new Student("zs",20));
        set.add(new Student("zs",20));
        set.add(new Student("zs",20));
        for (Student stu : set){
            System.out.println(stu);
        }
    }

}
