package com.zhyea.jspy.agent.tools.common;

public class Paper {

    private String name;

    public Paper(String name) {
        this.name = name;
    }

    public String content() {
        return "很多很多字";
    }


    @Override
    public String toString() {
        return "Paper{" +
                "name='" + name + '\'' +
                '}';
    }
}
