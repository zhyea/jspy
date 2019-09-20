package org.chobit.jspy.constants;

import java.lang.management.MemoryType;

public abstract class MemoryNames {

    public static String nameOf(MemoryType type) {
        switch (type) {
            case HEAP:
                return "堆内存使用量";
            case NON_HEAP:
                return "非堆内存使用量";
            default:
                return "";
        }
    }

}
