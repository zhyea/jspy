package org.chobit.jspy.agent.tools.common;

public class Box {

    private static String material = "都是纸的";

    private String name;

    private Fruit fruit;

    public Box(String name, Fruit fruit) {
        this.name = name;
        this.fruit = fruit;
    }


    @Override
    public String toString() {
        return "Box{" +
                "name='" + name + '\'' +
                '}';
    }
}
