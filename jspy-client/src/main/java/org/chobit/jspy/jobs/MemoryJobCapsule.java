package org.chobit.jspy.jobs;

import org.quartz.Job;

import static org.chobit.jspy.Config.MEMORY_COLLECT_INTERVAL_SECONDS;

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
        return MEMORY_COLLECT_INTERVAL_SECONDS;
    }
}
