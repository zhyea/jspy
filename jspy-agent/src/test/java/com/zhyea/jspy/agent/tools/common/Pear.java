package com.zhyea.jspy.agent.tools.common;

public class Pear implements Fruit {

    private String name;

    public Pear(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return null == name ? "梨" : name;
    }

    @Override
    public String toString() {
        return "Pear{" +
                "name='" + name + '\'' +
                '}';
    }
}
