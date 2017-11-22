package com.zhyea.jspy.commons.model;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 计时记录
 */
public class TimerRecord {

    private String id;

    private String methodName;

    private AtomicInteger count;

    private AtomicLong usedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    public AtomicLong getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(AtomicLong usedTime) {
        this.usedTime = usedTime;
    }
}
