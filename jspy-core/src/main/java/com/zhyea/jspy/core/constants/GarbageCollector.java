package com.zhyea.jspy.core.constants;


import static com.zhyea.jspy.commons.tools.StringUtils.isBlank;
import static com.zhyea.jspy.core.constants.GcType.FGC;
import static com.zhyea.jspy.core.constants.GcType.YGC;

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

    public final GcType type;

    public final String fullName;

    GarbageCollector(GcType type, String fullName) {
        this.type = type;
        this.fullName = fullName;
    }

    /**
     * 根据名称或全名获取GarbageCollector实例
     *
     * @param name 名称或全名
     * @return GarbageCollector实例
     */
    public static GarbageCollector nameOf(String name) {
        if (isBlank(name)) return null;

        GarbageCollector collector = null;
        try {
            collector = GarbageCollector.valueOf(name);
        } catch (Exception e) {
        }

        if (null != collector) return collector;

        for (GarbageCollector gc : values()) {
            if (name.equalsIgnoreCase(gc.fullName)) {
                return gc;
            }
        }

        return null;
    }


}
