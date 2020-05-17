package com.Kotori.Playground.List;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/***
 * Iterator和ListIterator主要区别在以下方面：
 *
 * 1. ListIterator有add()方法，可以向List中添加对象，而Iterator不能zd
 *
 * 2. ListIterator和专Iterator都有hasNext()和next()方法，可以实现顺序向后遍历，但是ListIterator有
 *    hasPrevious()和previous()方法，可以实现逆向（顺序向前）遍历。Iterator就不可以。
 *    
 * 3. ListIterator可以定位当前的索引位置，nextIndex()和previousIndex()可以实现。Iterator没有此功能。
 *
 * 4. 都可实现删除对象，但是ListIterator可以实现对象的修改，set()方法可属以实现。Iierator仅能遍历，不能修改。
 */
public class MyLinkedList {
    @Test
    public void test1(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(11);
        list.add(12);
        list.add(12);
        list.add(13);
        list.add(13);
        list.add(14);
        list.add(15);

        System.out.println(list);

        ListIterator<Integer> it = list.listIterator();
//        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer curNum = it.next();
            if (curNum == 12) {
                it.set(2);
            }
        }
        System.out.println(list);
    }
}
