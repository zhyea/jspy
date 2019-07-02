package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.quartz.JobExecutionContext;

public class GCJobCapsule extends JobCapsule {

    public GCJobCapsule() {
    }

    public GCJobCapsule(JSpyConfig config) {
        super(config);
    }


    @Override
    public String name() {
        return "gc";
    }

    @Override
    public String group() {
        return "gc";
    }

    @Override
    int intervalSeconds() {
        return config.getGcCollectIntervalSeconds();
    }


    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(context.getJobDetail().getKey());
        System.out.println("-------------------------------");
    }
}
