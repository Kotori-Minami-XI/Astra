package com.Kotori.Playground.io.Serial;

import org.junit.Test;

import java.io.*;

public class SerialDemo {
    private String filePath = "C:\\Users\\xz\\Desktop\\person.txt";
    @Test
    public void testSerialize() throws IOException {
        Person person = new Person("Kotori",15,"lovelive");

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(person);
    }

    @Test
    public void testDeserialize() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person person = (Person)objectInputStream.readObject();
        System.out.println(person);
    }
}

class Person implements Serializable {
    private String name;
    private Integer age;
    private String desc;

    public Person(String name, Integer age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                '}';
    }
}
