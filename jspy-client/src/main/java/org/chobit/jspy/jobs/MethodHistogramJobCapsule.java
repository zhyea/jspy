package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.model.ThreadGauge;

import static org.chobit.jspy.core.gauge.Threads.*;

public final class MethodHistogramJobCapsule extends JobCapsule<ThreadGauge> {


    public MethodHistogramJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/method/receive";
    }


    @Override
    String name() {
        return "method";
    }

    @Override
    int intervalSeconds() {
        return config.getThreadCollectIntervalSeconds();
    }

    @Override
    public ThreadGauge collect() {
        ThreadGauge gauge = new ThreadGauge();

        gauge.setCurrent(THREAD_COUNT.value());
        gauge.setPeak(PEAK_THREAD_COUNT.value());
        gauge.setTotalStarted(TOTAL_STARTED_THREAD_COUNT.value());
        gauge.setDaemon(DAEMON_THREAD_COUNT.value());

        return gauge;
    }
}
