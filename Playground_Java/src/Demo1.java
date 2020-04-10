import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

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


interface inter{

}

class A implements  inter{

}

public class Demo1 {

    @Test
    public void test2(){
        inter it = new A();
    }




}
