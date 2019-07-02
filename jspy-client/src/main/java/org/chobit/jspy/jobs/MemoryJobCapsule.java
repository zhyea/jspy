package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.quartz.JobExecutionContext;

public class MemoryJobCapsule extends JobCapsule {

    public MemoryJobCapsule(JSpyConfig config) {
        super(config);
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
    public void execute(JobExecutionContext context) {
        System.out.println(context.getJobDetail().getKey());
        System.out.println("-------------------------------");
    }
}
