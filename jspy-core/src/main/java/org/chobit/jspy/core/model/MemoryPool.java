package org.chobit.jspy.core.model;

import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;

public class MemoryPool {

    private String name;

    private MemoryType type;

    private String[] memoryManagerNames;

    private String host;

    private MemoryUsage usage;

    private MemoryUsage peakUsage;

    public MemoryPool(String name, MemoryType type, String[] memoryManagerNames, String host, MemoryUsage memoryUsage, MemoryUsage peakUsage) {
        this.name = name;
        this.type = type;
        this.memoryManagerNames = memoryManagerNames;
        this.host = host;
        this.usage = memoryUsage;
        this.peakUsage = peakUsage;
    }

    public String getName() {
        return name;
    }

    public MemoryType getType() {
        return type;
    }

    public String[] getMemoryManagerNames() {
        return memoryManagerNames;
    }

    public MemoryUsage getUsage() {
        return usage;
    }

    public MemoryUsage getPeakUsage() {
        return peakUsage;
    }
}
