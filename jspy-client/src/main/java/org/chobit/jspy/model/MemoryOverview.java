package org.chobit.jspy.model;

import org.chobit.jspy.core.model.MemoryInfo;
import org.chobit.jspy.core.model.MemoryPool;

import java.lang.management.MemoryUsage;
import java.util.List;

public class MemoryOverview {

    private long time;

    private MemoryInfo heapUsage;

    private MemoryInfo nonHeapUsage;

    private MemoryInfo peakHeapUsage;

    private MemoryInfo peakNonHeapUsage;

    private List<MemoryPool> memoryPools;


    public MemoryOverview() {
    }

    public MemoryOverview(long time,
                          MemoryUsage heapUsage,
                          MemoryUsage nonHeapUsage,
                          MemoryUsage peakHeapUsage,
                          MemoryUsage peakNonHeapUsage,
                          List<MemoryPool> memoryPools) {
        this.time = time;
        this.heapUsage = new MemoryInfo(heapUsage);
        this.nonHeapUsage = new MemoryInfo(nonHeapUsage);
        this.peakHeapUsage = new MemoryInfo(peakHeapUsage);
        this.peakNonHeapUsage = new MemoryInfo(peakNonHeapUsage);
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

    public MemoryInfo getPeakHeapUsage() {
        return peakHeapUsage;
    }

    public MemoryInfo getPeakNonHeapUsage() {
        return peakNonHeapUsage;
    }

    public List<MemoryPool> getMemoryPools() {
        return memoryPools;
    }
}
