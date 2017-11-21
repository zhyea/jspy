package com.zhyea.jspy.sample.model;

import com.zhyea.jspy.agent.tools.Watcher;

public class Account {

    public void operation() {
        long start = System.currentTimeMillis();
        System.out.println("operation...");
        long time = System.currentTimeMillis() - start;
        Watcher.add("methodName", time);
    }

}
