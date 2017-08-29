package com.zhyea.jspy.model;

public class MemoryPool {

    private final String name;
    private final boolean isHeap;
    private final boolean isValid;
    private final boolean collectionThresholdSupported;
    private final boolean usageThresholdSupported;
    private String[] memoryManagerNames;
    private long usageThreshold;
    private long collectionThreshold;

    public MemoryPool(String name,
                      boolean isHeap,
                      boolean isValid,
                      boolean collectionThresholdSupported,
                      boolean usageThresholdSupported) {
        this.name = name;
        this.isHeap = isHeap;
        this.isValid = isValid;
        this.collectionThresholdSupported = collectionThresholdSupported;
        this.usageThresholdSupported = usageThresholdSupported;
    }

    public String getName() {
        return name;
    }

    public boolean isHeap() {
        return isHeap;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isCollectionThresholdSupported() {
        return collectionThresholdSupported;
    }

    public boolean isUsageThresholdSupported() {
        return usageThresholdSupported;
    }

    public String[] getMemoryManagerNames() {
        return memoryManagerNames;
    }

    public void setMemoryManagerNames(String[] memoryManagerNames) {
        this.memoryManagerNames = memoryManagerNames;
    }

    public long getUsageThreshold() {
        return usageThreshold;
    }

    public void setUsageThreshold(long usageThreshold) {
        this.usageThreshold = usageThreshold;
    }

    public long getCollectionThreshold() {
        return collectionThreshold;
    }

    public void setCollectionThreshold(long collectionThreshold) {
        this.collectionThreshold = collectionThreshold;
    }
}
