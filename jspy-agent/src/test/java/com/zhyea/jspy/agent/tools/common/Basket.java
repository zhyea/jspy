package com.zhyea.jspy.agent.tools.common;

public class Basket {

    private int id = 12;

    private static Apple[] apples = {
            new Apple("苹果1"),
            new Apple("苹果2"),
            new Apple("苹果3"),
            new Apple("苹果4")
    };

    private Apple apple = new Apple("大红苹果");

    private Fruit pear = new Pear("大鸭梨");

    private Box paper = new Box("圣诞礼盒", new Apple("红苹果"));

}
