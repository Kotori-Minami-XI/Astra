import org.junit.Test;

public class Demo1 {
    @Test
    public void test() {
        A a1= new A();
        A.a=1;
        A a2 = new A();
        System.out.println(A.a);
        A b = new B();
        System.out.println(B.a);
        b.f();
    }
}

class A {
    static Integer a = 5;
    static void f (){
        System.out.println("A");
    }
}

class B extends A {
    static void f (){
        System.out.println("B");
    }
}
