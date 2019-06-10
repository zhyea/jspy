package org.chobit.jspy.jobs;

import org.quartz.Job;

public class MemoryJobCapsule extends JobCapsule {

    @Override
    String name() {
        return "memory";
    }

    @Override
    String group() {
        return "memory";
    }

    @Override
    Class<? extends Job> jobClass() {
        return MemoryJob.class;
    }

    @Override
    int intervalSeconds() {
        return 1;
    }
}
