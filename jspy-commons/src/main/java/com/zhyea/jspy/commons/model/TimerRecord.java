package com.zhyea.jspy.commons.model;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 计时记录
 */
public class TimerRecord {

    /**
     * 记录ID，方法名的MD5值
     */
    private String id;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法执行次数
     */
    private AtomicInteger count;

    /**
     * 方法执行时间
     */
    private AtomicLong totalUsedTime;


    public TimerRecord(String id, String methodName) {
        this.id = id;
        this.methodName = methodName;
        this.count = new AtomicInteger(0);
        this.totalUsedTime = new AtomicLong(0);
    }

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

    public AtomicLong getTotalUsedTime() {
        return totalUsedTime;
    }

    public void setTotalUsedTime(AtomicLong totalUsedTime) {
        this.totalUsedTime = totalUsedTime;
    }
}
