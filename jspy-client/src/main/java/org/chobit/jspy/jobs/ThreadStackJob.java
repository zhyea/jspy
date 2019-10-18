package org.chobit.jspy.jobs;

import okhttp3.HttpUrl;
import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.model.ThreadInfo;
import org.chobit.jspy.model.ThreadOverview;
import org.chobit.jspy.utils.SysTime;

import java.util.List;

import static org.chobit.jspy.core.gauge.ThreadsGaugeManager.allThreads;
import static org.chobit.jspy.utils.HTTP.post;

public final class ThreadStackJob extends AbstractQuartzJob {


    private static final String RECEIVE_PATH = "/api/thread/receive";

    public ThreadStackJob(JSpyConfig config) {
        super(config);
    }


    @Override
    String name() {
        return "threadStack";
    }

    @Override
    int intervalSeconds() {
        return config.getThreadCollectIntervalSeconds();
    }

    @Override
    void collect() {
        List<ThreadInfo> threads = allThreads();

        HttpUrl url = receiveUrl(RECEIVE_PATH);
        long time = SysTime.millis();
        post(url, headers(), new ThreadOverview(time, null, threads));
    }
}
