package com.zhyea.jspy.core.constants;

public enum Magnitude {

    KB(1024),
    MB(1024 * 1024),
    GB(1024 * 1024 * 1024),
    TB(1024 * 1024 * 1024 * 1024),
    PB(1024 * 1024 * 1024 * 1024 * 1024);

    public final long value;

    Magnitude(long value) {
        this.value = value;
    }

    public long compute(long size) {
        return size / this.value;
    }

}
