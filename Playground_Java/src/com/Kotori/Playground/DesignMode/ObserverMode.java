package com.Kotori.Playground.DesignMode;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

interface AbstractObserver {
    public void addObserver(AbstractObserver ob);
    public void delObserver(AbstractObserver ob);
    public void update(String msg);
}

class ConcreteObserver implements AbstractObserver, Comparable {
    String name;
    Integer id;
    Set<AbstractObserver> observerSet;

    @Override
    public int compareTo(Object o) {
        ConcreteObserver ob = (ConcreteObserver) o;
        if (ob.id == this.id)
            return 0;
        if (ob.id <= this.id)
            return 1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConcreteObserver that = (ConcreteObserver) o;

        if (!name.equals(that.name)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    public ConcreteObserver(String name, Integer id) {
        super();
        this.name = name;
        this.id = id;
        observerSet = new TreeSet();
    }

    public void addObserver(AbstractObserver ob){
        observerSet.add(ob);
    }

    public void delObserver(AbstractObserver ob) {
        observerSet.remove(ob);
    }

    public void update(String msg){
        System.out.println(this.name + "发送了消息:" + msg);
        for (AbstractObserver ob : observerSet){
            ((ConcreteObserver)ob).showMsg(msg);
        }
    }

    private void showMsg(String msg){
        System.out.println(this.name + "收到了消息:" + msg);
    }
}

public class ObserverMode{
    @Test
    public void test1(){
        AbstractObserver ob1 = new ConcreteObserver("zs",1);
        AbstractObserver ob2 = new ConcreteObserver("ls",2);
        AbstractObserver ob3 = new ConcreteObserver("wc",3);
        AbstractObserver star = new ConcreteObserver("蔡徐坤", 0);

        star.addObserver(ob1);
        star.addObserver(ob2);
        star.addObserver(ob3);
        star.update("我会唱跳");

    }
}
