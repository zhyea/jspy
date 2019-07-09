package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.model.ClassLoadingGauge;

import static org.chobit.jspy.core.gauge.ClassLoading.*;

public final class ClassLoadingJobCapsule extends JobCapsule<ClassLoadingGauge> {


    public ClassLoadingJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/class-load/receive";
    }

    @Override
    String name() {
        return "classLoading";
    }

    @Override
    int intervalSeconds() {
        return config.getClassLoadingCollectIntervalSeconds();
    }

    @Override
    ClassLoadingGauge collect() {
        ClassLoadingGauge gauge = new ClassLoadingGauge();
        gauge.setTotalLoaded(TOTAL_LOADED.value());
        gauge.setCurrentLoaded(CURRENT_LOADED.value());
        gauge.setUnloaded(UNLOADED.value());
        return gauge;
    }
}
