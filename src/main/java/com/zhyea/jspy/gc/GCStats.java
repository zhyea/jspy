package com.zhyea.jspy.gc;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class GCStats {

    private static AtomicLong countYoungGC = new AtomicLong(0L);

    private static AtomicLong timeYoungGCUsed = new AtomicLong(0L);

    private static AtomicLong countOldGC = new AtomicLong(0L);

    private static AtomicLong timeOldGCUsed = new AtomicLong(0L);

    private static volatile long lastCountTime = System.currentTimeMillis();

    private static final String[] youngGenCollectorNames = {
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


    private static final String[] oldGenCollectorNames = {
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
        Arrays.sort(youngGenCollectorNames);
        Arrays.sort(oldGenCollectorNames);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                countGC();
            }
        }, 0, 30, TimeUnit.SECONDS);
    }


    private static final synchronized void countGC() {
        clean();
        List<GarbageCollectorMXBean> list = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : list) {
            String name = bean.getName();
            if (Arrays.binarySearch(oldGenCollectorNames, name) >= 0) {
                countOldGC.getAndAdd(bean.getCollectionCount());
                timeOldGCUsed.getAndAdd(bean.getCollectionTime());
            } else if (Arrays.binarySearch(youngGenCollectorNames, name) >= 0) {
                countYoungGC.getAndAdd(bean.getCollectionCount());
                timeYoungGCUsed.getAndAdd(bean.getCollectionTime());
            }
        }
        lastCountTime = System.currentTimeMillis();
    }

    private static final void clean() {
        countYoungGC.set(0L);
        countOldGC.set(0L);
        timeOldGCUsed.set(0L);
        timeOldGCUsed.set(0L);
    }

    private static final void checkOrCount() {
        if (System.currentTimeMillis() - lastCountTime > 1000 * 10) {
            countGC();
        }
    }

    public static final long countYoungGC() {
        checkOrCount();
        return countYoungGC.get();
    }


    public static final long countFullGC() {
        checkOrCount();
        return countOldGC.get();
    }


    public static final long timeYoungGCUsed() {
        checkOrCount();
        return timeYoungGCUsed.get();
    }

    public static final long timeOldGCUsed() {
        checkOrCount();
        return timeOldGCUsed.get();
    }

}
