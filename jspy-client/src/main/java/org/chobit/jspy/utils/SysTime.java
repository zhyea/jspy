package org.chobit.jspy.utils;

import java.util.concurrent.TimeUnit;

public abstract class SysTime {


    public static long millis() {
        return System.currentTimeMillis();
    }

    
    public static long nano() {
        return System.nanoTime();
    }


    public static void sleepInSeconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private SysTime() {
    }
}
