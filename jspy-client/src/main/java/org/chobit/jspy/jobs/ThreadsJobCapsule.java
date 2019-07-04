package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;

public class ThreadsJobCapsule extends JobCapsule {

    
    public ThreadsJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/threads/receive";
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
    public Object collect() {
        return null;
    }
}
