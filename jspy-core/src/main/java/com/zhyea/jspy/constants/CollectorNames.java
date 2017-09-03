package com.zhyea.jspy.constants;

import java.util.Arrays;

public class CollectorNames {


    public static final String[] YOUNG_GEN_COLLECTOR_NAMES = {
            /* Oracle (Sun) HotSpot  */
            // -XX:+UseSerialGC
            "Copy",
            // -XX:+UseParNewGC
            "ParNew",
            // -XX:+UseParallelGC
            "PS Scavenge",

            /* Oracle (BEA) JRockit */
            // -XgcPrio:pausetime
            "Garbage collection optimized for short pausetimes Young Collector",
            // -XgcPrio:throughput
            "Garbage collection optimized for throughput Young Collector",
            // -XgcPrio:deterministic
            "Garbage collection optimized for deterministic pausetimes Young Collector"};


    public static final String[] OLD_GEN_COLLECTOR_NAMES = {
            /* Oracle (Sun) HotSpot */
            // -XX:+UseSerialGC
            "MarkSweepCompact",
            // -XX:+UseParallelGC and (-XX:+UseParallelOldGC or -XX:+UseParallelOldGCCompacting)
            "PS MarkSweep",
            // -XX:+UseConcMarkSweepGC
            "ConcurrentMarkSweep",

            /* Oracle (BEA) JRockit */
            // -XgcPrio:pausetime
            "Garbage collection optimized for short pausetimes Old Collector",
            // -XgcPrio:throughput
            "Garbage collection optimized for throughput Old Collector",
            // -XgcPrio:deterministic
            "Garbage collection optimized for deterministic pausetimes Old Collector"};


    static {
        Arrays.sort(YOUNG_GEN_COLLECTOR_NAMES);
        Arrays.sort(OLD_GEN_COLLECTOR_NAMES);

        //System.out.println(Arrays.toString(OLD_GEN_COLLECTOR_NAMES));
        //System.out.println(Arrays.toString(YOUNG_GEN_COLLECTOR_NAMES));
    }
}
