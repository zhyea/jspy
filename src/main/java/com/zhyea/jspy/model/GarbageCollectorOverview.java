package com.zhyea.jspy.model;

import java.util.concurrent.atomic.AtomicLong;

public class GarbageCollectorOverview {


    private AtomicLong youngCollectCount = new AtomicLong(0L);

    private AtomicLong youngCollectTime = new AtomicLong(0L);

    private AtomicLong oldCollectCount = new AtomicLong(0L);

    private AtomicLong oldCollectTime = new AtomicLong(0L);


    public long addAndGetYoungCollectCount(long count) {
        return this.youngCollectCount.addAndGet(count);
    }


    public long getYoungCollectorCount() {
        return this.youngCollectCount.get();
    }


    public long addAndGetYoungCollectTime(long time) {
        return this.youngCollectTime.addAndGet(time);
    }

    public long getYoungCollectTime() {
        return this.youngCollectTime.get();
    }

    public long addAndGetOldCollectCount(long count) {
        return this.oldCollectCount.addAndGet(count);
    }

    public long getOldCollectCount() {
        return this.oldCollectCount.get();
    }

    public long addAndGetOldCollectTime(long time) {
        return this.oldCollectTime.addAndGet(time);
    }

    public long getOldCollectTime() {
        return this.oldCollectTime.get();
    }
}
