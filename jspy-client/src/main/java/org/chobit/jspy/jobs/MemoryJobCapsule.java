package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.gauge.MemoryGaugeManager;
import org.chobit.jspy.core.model.MemoryPool;
import org.chobit.jspy.model.MemoryOverview;

import java.lang.management.MemoryUsage;
import java.util.List;

import static org.chobit.jspy.core.info.Net.LOCAL_HOST_IP;

public class MemoryJobCapsule extends JobCapsule {

    public MemoryJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/memory/receive";
    }

    @Override
    String name() {
        return "memory";
    }

    @Override
    String group() {
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
        String ip = LOCAL_HOST_IP.value();

        return new MemoryOverview(System.currentTimeMillis(), ip, heapUsage, nonHeapUsage, memoryPools);
    }
}
