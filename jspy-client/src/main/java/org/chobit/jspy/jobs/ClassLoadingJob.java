package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.model.ClassLoadingCount;

import static org.chobit.jspy.core.gauge.ClassLoading.*;

public final class ClassLoadingJob extends AbstractQuartzJob {


    public ClassLoadingJob(JSpyConfig config) {
        super(config);
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
    void collect() {
        ClassLoadingCount gauge = new ClassLoadingCount();
        gauge.setTotalLoaded(TOTAL_LOADED.value());
        gauge.setCurrentLoaded(CURRENT_LOADED.value());
        gauge.setUnloaded(UNLOADED.value());

        messagePack().addClass(gauge);
    }
}
