package org.chobit.jspy.core.model;

import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;

public class MemoryPool {

    private String name;

    private MemoryType type;

    private String[] memoryManagerNames;

    private MemoryInfo usage;

    private MemoryInfo peakUsage;

    public MemoryPool() {
    }

    public MemoryPool(String name, MemoryType type, String[] memoryManagerNames, MemoryUsage memoryUsage, MemoryUsage peakUsage) {
        this.name = name;
        this.type = type;
        this.memoryManagerNames = memoryManagerNames;
        this.usage = new MemoryInfo(memoryUsage);
        this.peakUsage = new MemoryInfo(peakUsage);
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

    public MemoryInfo getUsage() {
        return usage;
    }

    public MemoryInfo getPeakUsage() {
        return peakUsage;
    }
}
