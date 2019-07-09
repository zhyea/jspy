package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;

public class ClassLoadingJobCapsule extends JobCapsule<Object> {


    public ClassLoadingJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return null;
    }

    @Override
    String name() {
        return null;
    }

    @Override
    String group() {
        return null;
    }

    @Override
    int intervalSeconds() {
        return 0;
    }

    @Override
    Object collect() {
        return null;
    }
}
