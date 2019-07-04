package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;

public class GCJobCapsule extends JobCapsule {

    public GCJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/gc/receive";
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
    public Object collect() {
        return null;
    }
}
