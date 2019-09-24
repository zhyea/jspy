package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.model.MemoryPool;
import org.chobit.jspy.model.MemoryOverview;

import java.lang.management.MemoryUsage;
import java.util.List;

import static org.chobit.jspy.core.gauge.MemoryGaugeManager.*;
import static org.chobit.jspy.utils.SysTime.millis;

public final class MemoryJob extends AbstractQuartzJob {

    public MemoryJob(JSpyConfig config) {
        super(config);
    }

    @Override
    String name() {
        return "memory";
    }

    @Override
    int intervalSeconds() {
        return config.getMemoryCollectIntervalSeconds();
    }


    private MemoryUsage peakHeapUsage;

    private MemoryUsage peakNonHeapUsage;

    @Override
    void collect() {

        MemoryUsage heapUsage = heapMemoryUsage();
        MemoryUsage nonHeapUsage = nonHeapMemoryUsage();
        List<MemoryPool> memoryPools = memoryPools();

        if (null == peakHeapUsage || compareMemoryUsage(heapUsage, peakHeapUsage)) {
            this.peakHeapUsage = heapUsage;
        }
        if (null == peakNonHeapUsage || compareMemoryUsage(nonHeapUsage, peakNonHeapUsage)) {
            this.peakNonHeapUsage = nonHeapUsage;
        }

        MemoryOverview mem = new MemoryOverview(millis(), heapUsage, nonHeapUsage, peakHeapUsage, peakNonHeapUsage, memoryPools);
        messagePack().addMemory(mem);
    }
}
