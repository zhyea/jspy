package org.chobit.jspy.jobs;

import org.quartz.Job;

public class GCJobCapsule extends JobCapsule {


    @Override
    public String name() {
        return "gc";
    }

    @Override
    public String group() {
        return "gc";
    }

    @Override
    Class<? extends Job> jobClass() {
        return GCJob.class;
    }

    @Override
    int intervalSeconds() {
        return 1;
    }


}
