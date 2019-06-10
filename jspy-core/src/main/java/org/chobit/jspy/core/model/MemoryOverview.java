package org.chobit.jspy.core.model;

import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.List;

public class MemoryOverview {

    private String appCode;

    private String host;

    private Date time;

    private MemoryUsage heapUsage;

    private MemoryUsage nonHeapUsage;

    private List<MemoryPool> memoryPools;


    public MemoryOverview(String appCode,
                          String host,
                          Date time,
                          MemoryUsage heapUsage,
                          MemoryUsage nonHeapUsage,
                          List<MemoryPool> memoryPools) {
        this.appCode = appCode;
        this.host = host;
        this.time = time;
        this.heapUsage = heapUsage;
        this.nonHeapUsage = nonHeapUsage;
        this.memoryPools = memoryPools;
    }

    public String getAppCode() {
        return appCode;
    }

    public String getHost() {
        return host;
    }

    public Date getTime() {
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
