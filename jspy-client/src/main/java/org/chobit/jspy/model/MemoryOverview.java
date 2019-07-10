package org.chobit.jspy.model;

import org.chobit.jspy.core.model.MemoryInfo;
import org.chobit.jspy.core.model.MemoryPool;

import java.lang.management.MemoryUsage;
import java.util.List;

public class MemoryOverview {

    private long time;

    private MemoryInfo heapUsage;

    private MemoryInfo nonHeapUsage;

    private List<MemoryPool> memoryPools;


    public MemoryOverview() {
    }

    public MemoryOverview(long time,
                          MemoryUsage heapUsage,
                          MemoryUsage nonHeapUsage,
                          List<MemoryPool> memoryPools) {
        this.time = time;
        this.heapUsage = new MemoryInfo(heapUsage);
        this.nonHeapUsage = new MemoryInfo(nonHeapUsage);
        this.memoryPools = memoryPools;
    }


    public long getTime() {
        return time;
    }

    public MemoryInfo getHeapUsage() {
        return heapUsage;
    }

    public MemoryInfo getNonHeapUsage() {
        return nonHeapUsage;
    }

    public List<MemoryPool> getMemoryPools() {
        return memoryPools;
    }
}
