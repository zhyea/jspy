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

}
