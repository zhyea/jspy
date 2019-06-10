package org.chobit.jspy.jobs;

import org.quartz.Job;

public class ThreadsJobCapsule extends JobCapsule {

    @Override
    String name() {
        return "threads";
    }

    @Override
    String group() {
        return "threads";
    }

    @Override
    Class<? extends Job> jobClass() {
        return ThreadsJob.class;
    }

    @Override
    int intervalSeconds() {
        return 1;
    }
}
