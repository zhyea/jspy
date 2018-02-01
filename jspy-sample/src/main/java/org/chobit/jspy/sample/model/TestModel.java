package org.chobit.jspy.sample.model;

import org.chobit.jspy.annotations.JSpyTimer;

/**
 * 测试类
 */
public final class TestModel {


    public void operation() {
        System.out.println("operation...");
    }


    @JSpyTimer
    public String operation(int arg1, int arg2, String arg3) {
        System.out.println("abc");
        return arg1 + arg2 + arg3;
    }

    @JSpyTimer
    private void op() {

    }

    @JSpyTimer
    private static void op2() {

    }


}
