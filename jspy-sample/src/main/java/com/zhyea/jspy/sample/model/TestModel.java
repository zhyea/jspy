package com.zhyea.jspy.sample.model;

import com.zhyea.jspy.agent.tools.Watcher;

public class TestModel {

    private int member = 0;

    public void operation() {
        long start = System.currentTimeMillis();
        System.out.println("operation...");
        long time = System.currentTimeMillis() - start;
        Watcher.add("methodName", time);
    }

}
