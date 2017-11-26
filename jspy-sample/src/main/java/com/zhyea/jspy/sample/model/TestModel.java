package com.zhyea.jspy.sample.model;

import com.zhyea.jspy.annotations.JSpyTimer;

/**
 * 测试类
 */
@JSpyTimer
public class TestModel {


    private int member = 0;

    @JSpyTimer
    public void operation() {
        System.out.println("operation...");
    }


    public String op2() {
        System.out.println("this is a test operation...");
        return null;
    }

    public String op3() {
        try {
            System.out.println("this is a test operation...");
            return null;
        } finally {
            System.out.println("the finally block");
        }
    }

    public int getMember(){
        return member;
    }

}
