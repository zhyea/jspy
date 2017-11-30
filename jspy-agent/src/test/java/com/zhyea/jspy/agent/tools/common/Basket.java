package com.zhyea.jspy.agent.tools.common;

public class Basket {

    private int id = 12;

    private byte[] arr = new byte[1024];

    private static Apple apple0 = new Apple("留给自己吃的大苹果");


    private static int[] arrInt = {1, 2, 4, 5, 7, 8, 9};

    private static Apple[] apples = {
            new Apple("苹果1"), new Apple("苹果2"), new Apple("苹果3"), new Apple("苹果4")
    };

    private Basket[] baskets = new Basket[12];

    private Apple apple = new Apple("大红苹果");

    private Fruit pear = new Pear("大鸭梨");

    private Paper paper = new Paper("白色的宣纸");

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", apple=" + apple +
                ", pear=" + pear +
                ", paper=" + paper +
                '}';
    }
}
