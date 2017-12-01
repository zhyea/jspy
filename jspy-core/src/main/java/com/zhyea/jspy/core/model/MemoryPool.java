package com.zhyea.jspy.core.model;

import java.lang.management.MemoryUsage;

public class MemoryPool {

    private String name;

    private String[] memoryManagerNames;

    private MemoryUsage memoryUsage;

    private MemoryUsage peakUsage;

    private boolean isUsageThresholdSupported;

    private long usageThreshold;

    private long usageThresholdCount;

    private boolean isUsageThresholdExceeded;

    private boolean isCollectionUsageThresholdSupported;

    private long collectionUsageThreshold;

    private long collectionUsageThresholdCount;

    private boolean isCollectionUsageThresholdExceeded;


    public MemoryPool(String name, MemoryUsage memoryUsage) {
        this.name = name;
        this.memoryUsage = memoryUsage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getMemoryManagerNames() {
        return memoryManagerNames;
    }

    public void setMemoryManagerNames(String[] memoryManagerNames) {
        this.memoryManagerNames = memoryManagerNames;
    }

    public MemoryUsage getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(MemoryUsage memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public MemoryUsage getPeakUsage() {
        return peakUsage;
    }

    public void setPeakUsage(MemoryUsage peakUsage) {
        this.peakUsage = peakUsage;
    }

    public boolean isUsageThresholdSupported() {
        return isUsageThresholdSupported;
    }

    public void setUsageThresholdSupported(boolean usageThresholdSupported) {
        isUsageThresholdSupported = usageThresholdSupported;
    }

    public long getUsageThreshold() {
        return usageThreshold;
    }

    public void setUsageThreshold(long usageThreshold) {
        this.usageThreshold = usageThreshold;
    }

    public long getUsageThresholdCount() {
        return usageThresholdCount;
    }

    public void setUsageThresholdCount(long usageThresholdCount) {
        this.usageThresholdCount = usageThresholdCount;
    }

    public boolean isUsageThresholdExceeded() {
        return isUsageThresholdExceeded;
    }

    public void setUsageThresholdExceeded(boolean usageThresholdExceeded) {
        isUsageThresholdExceeded = usageThresholdExceeded;
    }

    public boolean isCollectionUsageThresholdSupported() {
        return isCollectionUsageThresholdSupported;
    }

    public void setCollectionUsageThresholdSupported(boolean collectionUsageThresholdSupported) {
        isCollectionUsageThresholdSupported = collectionUsageThresholdSupported;
    }

    public long getCollectionUsageThreshold() {
        return collectionUsageThreshold;
    }

    public void setCollectionUsageThreshold(long collectionUsageThreshold) {
        this.collectionUsageThreshold = collectionUsageThreshold;
    }

    public long getCollectionUsageThresholdCount() {
        return collectionUsageThresholdCount;
    }

    public void setCollectionUsageThresholdCount(long collectionUsageThresholdCount) {
        this.collectionUsageThresholdCount = collectionUsageThresholdCount;
    }

    public boolean isCollectionUsageThresholdExceeded() {
        return isCollectionUsageThresholdExceeded;
    }

    public void setCollectionUsageThresholdExceeded(boolean collectionUsageThresholdExceeded) {
        isCollectionUsageThresholdExceeded = collectionUsageThresholdExceeded;
    }
}
