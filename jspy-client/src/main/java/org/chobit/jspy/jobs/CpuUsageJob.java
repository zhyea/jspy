package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.gauge.CPU;

public final class CpuUsageJob extends AbstractQuartzJob {


    public CpuUsageJob(JSpyConfig config) {
        super(config);
    }

    @Override
    String name() {
        return "cpuUsage";
    }

    @Override
    int intervalSeconds() {
        return config.getCpuUsageCollectIntervalSeconds();
    }

    @Override
    void collect() {
        messagePack().addCpuUsage(CPU.LOAD.value());
    }
}
