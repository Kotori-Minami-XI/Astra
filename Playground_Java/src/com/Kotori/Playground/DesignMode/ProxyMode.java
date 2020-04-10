package com.Kotori.Playground.DesignMode;

import org.junit.Test;

interface Protocol{
    void ListenCry();
    void ListenFight();
}

class Nanny implements Protocol{
    private String name;
    public Nanny(String name){
        this.name = name;
    }
    @Override
    public void ListenCry() {
        System.out.println(this.name + "处理baby哭闹");
    }

    @Override
    public void ListenFight() {
        System.out.println(this.name + "处理baby打架");
    }
}

class Baby{
    private Protocol proxy = null;

    public void setProxy(Protocol proxy) {
        this.proxy = proxy;
    }

    public void babyCry(){
        this.proxy.ListenCry();
    }

    public void babyFight(){
        this.proxy.ListenFight();
    }
}

public class ProxyMode {
    /***
     * 测试代理模式，一个满足proxy协议的Nanny监听一个baby的行为
     */
    @Test
    public void testProxy(){
        Baby baby = new Baby();
        baby.setProxy(new Nanny("保姆1"));
        baby.babyCry();
        baby.babyFight();

        //换保姆
        baby.setProxy(new Nanny("保姆2"));
        baby.babyCry();
        baby.babyFight();
    }
}
