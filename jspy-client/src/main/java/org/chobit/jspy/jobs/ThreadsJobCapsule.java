package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.quartz.JobExecutionContext;

public class ThreadsJobCapsule extends JobCapsule {

    
    public ThreadsJobCapsule() {
    }


    public ThreadsJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String name() {
        return "threads";
    }

    @Override
    String group() {
        return "threads";
    }


    @Override
    int intervalSeconds() {
        return config.getThreadCollectIntervalSeconds();
    }

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(context.getJobDetail().getKey());
        System.out.println("-------------------------------");
    }
}
