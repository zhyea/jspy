package com.zhyea.jspy.commons.model;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 计时记录
 */
public class TimerEntry {

    /**
     * 记录ID，方法名的MD5值
     */
    private String id;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法描述,包括方法描述及参数信息
     */
    private String methodDesc;

    /**
     * 方法执行次数
     */
    private AtomicInteger count;

    /**
     * 方法执行时间
     */
    private AtomicLong usedTime;


    public TimerEntry(String id, String methodName, String methodDesc) {
        this.id = id;
        this.methodName = methodName;
        this.methodDesc = methodDesc;
        this.count = new AtomicInteger(0);
        this.usedTime = new AtomicLong(0);
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

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
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


    @Override
    public String toString() {
        return "TimerEntry{" +
                "id='" + id + '\'' +
                ", methodName='" + methodName + '\'' +
                ", count=" + count +
                ", usedTime=" + usedTime +
                '}';
    }
}
