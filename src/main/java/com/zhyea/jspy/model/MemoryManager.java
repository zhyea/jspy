package com.zhyea.jspy.model;

public class MemoryManager {

    private final String name;

    private final boolean isValid;

    private final String[] memoryPoolNames;

    public MemoryManager(String name, boolean isValid, String[] memoryPoolNames) {
        this.name = name;
        this.isValid = isValid;
        this.memoryPoolNames = memoryPoolNames;
    }

    public String getName() {
        return name;
    }

    public boolean isValid() {
        return isValid;
    }

    public String[] getMemoryPoolNames() {
        return memoryPoolNames;
    }

}
