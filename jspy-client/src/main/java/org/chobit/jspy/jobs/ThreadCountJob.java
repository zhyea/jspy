package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.model.ThreadCount;
import org.chobit.jspy.model.ThreadOverview;
import org.chobit.jspy.utils.SysTime;

import static org.chobit.jspy.core.gauge.Threads.*;

public final class ThreadCountJob extends AbstractQuartzJob {


    public ThreadCountJob(JSpyConfig config) {
        super(config);
    }


    @Override
    String name() {
        return "threadCount";
    }

    @Override
    int intervalSeconds() {
        return config.getThreadCollectIntervalSeconds();
    }

    @Override
    void collect() {
        ThreadCount count = new ThreadCount();

        count.setCurrent(THREAD_COUNT.value());
        count.setPeak(PEAK_THREAD_COUNT.value());
        count.setTotalStarted(TOTAL_STARTED_THREAD_COUNT.value());
        count.setDaemon(DAEMON_THREAD_COUNT.value());

        long time = SysTime.millis();

        messagePack().addThread(new ThreadOverview(time, count, null));
    }
}
