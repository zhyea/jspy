package org.chobit.jspy.agent.tools.common;

public class Apple implements Fruit {

    private String name;

    public Apple(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                '}';
    }
}
