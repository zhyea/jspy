package com.zhyea.jspy.core.constants;


import static com.zhyea.jspy.core.constants.GarbageCollectType.FGC;
import static com.zhyea.jspy.core.constants.GarbageCollectType.YGC;

public enum GarbageCollector {

    /* Oracle (Sun) HotSpot  */
    // -XX:+UseSerialGC
    Copy(YGC, "Copy"),
    // -XX:+UseParNewGC
    ParNew(YGC, "ParNew"),
    // -XX:+UseParallelGC
    PSScavenge(YGC, "PS Scavenge"),
    /* Oracle (BEA) JRockit */
    // -XgcPrio:pausetime
    YoungPauseTime(YGC, "Garbage collection optimized for short pausetimes Young Collector"),
    //-XgcPrio:throughput
    YoungThroughput(YGC, "Garbage collection optimized for throughput Young Collector"),
    // -XgcPrio:deterministic
    YoungDeterministic(YGC, "Garbage collection optimized for deterministic pausetimes Young Collector"),

    /* Oracle (Sun) HotSpot */
    // -XX:+UseSerialGC
    MarkSweepCompact(FGC, "MarkSweepCompact"),
    // -XX:+UseParallelGC and (-XX:+UseParallelOldGC or -XX:+UseParallelOldGCCompacting)
    PSMarkSweep(FGC, "PS MarkSweep"),
    // -XX:+UseConcMarkSweepGC
    ConcurrentMarkSweep(FGC, "ConcurrentMarkSweep"),

    /* Oracle (BEA) JRockit */
    // -XgcPrio:pausetime
    OldPauseTime(FGC, "Garbage collection optimized for short pausetimes Old Collector"),
    //-XgcPrio:throughput
    OldThroughput(FGC, "Garbage collection optimized for throughput Old Collector"),
    // -XgcPrio:deterministic
    OldDeterministic(FGC, "Garbage collection optimized for deterministic pausetimes Old Collector");

    public final GarbageCollectType type;

    public final String name;

    GarbageCollector(GarbageCollectType type, String name) {
        this.type = type;
        this.name = name;
    }


}
