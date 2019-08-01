package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.gauge.MemoryGaugeManager;
import org.chobit.jspy.core.model.MemoryPool;
import org.chobit.jspy.model.MemoryOverview;

import java.lang.management.MemoryUsage;
import java.util.List;

import static org.chobit.jspy.utils.SysTime.millis;

public final class MemoryJobCapsule extends JobCapsule<MemoryOverview> {

    public MemoryJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/api/memory/receive";
    }

    @Override
    String name() {
        return "memory";
    }

    @Override
    int intervalSeconds() {
        return config.getMemoryCollectIntervalSeconds();
    }

    @Override
    public MemoryOverview collect() {

        MemoryUsage heapUsage = MemoryGaugeManager.heapMemoryUsage();
        MemoryUsage nonHeapUsage = MemoryGaugeManager.nonHeapMemoryUsage();
        List<MemoryPool> memoryPools = MemoryGaugeManager.memoryPools();

        return new MemoryOverview(millis(), heapUsage, nonHeapUsage, memoryPools);
    }
}
