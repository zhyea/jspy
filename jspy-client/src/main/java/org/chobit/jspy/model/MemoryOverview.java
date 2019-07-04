package org.chobit.jspy.model;

import org.chobit.jspy.core.model.MemoryPool;

import java.lang.management.MemoryUsage;
import java.util.List;

public class MemoryOverview {

    private long time;

    private String host;

    private MemoryUsage heapUsage;

    private MemoryUsage nonHeapUsage;

    private List<MemoryPool> memoryPools;


    public MemoryOverview(long time,
                          String host,
                          MemoryUsage heapUsage,
                          MemoryUsage nonHeapUsage,
                          List<MemoryPool> memoryPools) {
        this.host = host;
        this.time = time;
        this.heapUsage = heapUsage;
        this.nonHeapUsage = nonHeapUsage;
        this.memoryPools = memoryPools;
    }

    public String getHost() {
        return host;
    }

    public long getTime() {
        return time;
    }

    public MemoryUsage getHeapUsage() {
        return heapUsage;
    }

    public MemoryUsage getNonHeapUsage() {
        return nonHeapUsage;
    }

    public List<MemoryPool> getMemoryPools() {
        return memoryPools;
    }
}
