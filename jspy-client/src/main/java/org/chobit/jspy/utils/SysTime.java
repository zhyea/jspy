package org.chobit.jspy.utils;

public abstract class SysTime {


    public static long millis() {
        return System.currentTimeMillis();
    }

    
    public static long nano() {
        return System.nanoTime();
    }


    private SysTime() {
    }
}
