package com.zhyea.jspy.core.model;

import com.zhyea.jspy.core.constants.GcType;

import java.util.concurrent.atomic.AtomicLong;

public class GcSummary {

    private GcType type;

    /**
     * GC次数
     */
    private AtomicLong count = new AtomicLong(0);

    /**
     * GC执行时间
     */
    private AtomicLong usedTime = new AtomicLong(0L);

    public GcSummary(GcType type) {
        this.type = type;
    }

    public void accumulate(long count, long usedTime) {
        this.count.addAndGet(count);
        this.usedTime.addAndGet(usedTime);
    }

    public GcType getType() {
        return type;
    }

    public void setType(GcType type) {
        this.type = type;
    }

    public AtomicLong getCount() {
        return count;
    }

    public void setCount(AtomicLong count) {
        this.count = count;
    }

    public AtomicLong getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(AtomicLong usedTime) {
        this.usedTime = usedTime;
    }
}
