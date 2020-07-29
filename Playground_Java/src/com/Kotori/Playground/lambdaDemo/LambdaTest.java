package com.Kotori.Playground.lambdaDemo;

import org.junit.Test;

interface Operation {
    int operation(int a,int b);
}

public class LambdaTest {
    @Test
    public void testLambda1() {
        int result = 0;
        Operation addOperation = (int a, int b) -> { return a + b; };
        result = addOperation.operation(1, 2);
        System.out.println(result);

        Operation minusOperation = (int a, int b) -> { return a - b; };
        result = minusOperation.operation(1, 2);
        System.out.println(result);

        Operation divOperation = (int a, int b) -> { return a / b; };
        result = divOperation.operation(1, 2);
        System.out.println(result);

        Operation mulOperation = (int a, int b) -> { return a * b; };
        result = mulOperation.operation(1, 2);
        System.out.println(result);
    }

}
