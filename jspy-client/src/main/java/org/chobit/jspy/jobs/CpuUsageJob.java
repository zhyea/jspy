package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.gauge.CPU;

public class CpuUsageJob extends AbstractQuartzJob<Double> {


    public CpuUsageJob(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/api/cpu/receive";
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
    Double collect() {
        return CPU.LOAD.value();
    }
}
