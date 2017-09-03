package com.zhyea.jspy.model;

public class GarbageCollector extends MemoryManager {


    private long collectionCount;

    private long collectionTime;

    public GarbageCollector(String name, boolean isValid, String[] memoryPoolNames, long collectionCount, long collectionTime) {
        super(name, isValid, memoryPoolNames);
        this.collectionCount = collectionCount;
        this.collectionTime = collectionTime;
    }

    public long getCollectionCount() {
        return collectionCount;
    }


    public long getCollectionTime() {
        return collectionTime;
    }

}
