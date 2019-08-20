package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.model.ThreadInfo;
import org.chobit.jspy.model.ThreadCount;
import org.chobit.jspy.model.ThreadOverview;
import org.chobit.jspy.utils.SysTime;

import java.util.List;

import static org.chobit.jspy.core.gauge.Threads.*;
import static org.chobit.jspy.core.gauge.ThreadsGaugeManager.allThreads;

public final class ThreadsJobCapsule extends JobCapsule<ThreadOverview> {


    public ThreadsJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/api/thread/receive";
    }


    @Override
    String name() {
        return "threads";
    }

    @Override
    int intervalSeconds() {
        return config.getThreadCollectIntervalSeconds();
    }

    @Override
    public ThreadOverview collect() {
        ThreadCount count = new ThreadCount();

        count.setCurrent(THREAD_COUNT.value());
        count.setPeak(PEAK_THREAD_COUNT.value());
        count.setTotalStarted(TOTAL_STARTED_THREAD_COUNT.value());
        count.setDaemon(DAEMON_THREAD_COUNT.value());

        long time = SysTime.millis();

        List<ThreadInfo> threads = allThreads();

        return new ThreadOverview(time, count, threads);
    }
}
