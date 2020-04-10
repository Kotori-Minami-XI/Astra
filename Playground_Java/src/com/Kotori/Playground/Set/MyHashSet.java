package com.Kotori.Playground.Set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class MyHashSet {

    //内部类
    class Student{
        String name;
        Integer age;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            if (!name.equals(student.name)) return false;
            return age.equals(student.age);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age.hashCode();
            return result;
        }
    }
    /***
     * 测试去重情况
     * 对于HashSet来说只有重写equals和hashcode才能去重
     */
    @Test
    public void testHashSet(){
        Set<Student> set = new HashSet<Student>();
        set.add(new Student("zs",20));
        set.add(new Student("zs",20));
        set.add(new Student("zs",20));
        for (Student stu : set){
            System.out.println(stu);
        }
    }
}
