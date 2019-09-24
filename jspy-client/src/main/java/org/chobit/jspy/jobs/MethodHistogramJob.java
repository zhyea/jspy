package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.metrics.Snapshot;
import org.chobit.jspy.core.support.JSpyWatcherCollector;
import org.chobit.jspy.model.MethodHistogram;

import java.util.Map;

public final class MethodHistogramJob extends AbstractQuartzJob {


    public MethodHistogramJob(JSpyConfig config) {
        super(config);
    }

    @Override
    String name() {
        return "method";
    }

    @Override
    int intervalSeconds() {
        return config.getWatcherHistogramPeriodSeconds();
    }

    void collect() {
        JSpyWatcherCollector collector = JSpyWatcherCollector.getIfExists();
        if (null == collector || 0 == collector.size()) {
            return;
        }

        MethodHistogram mh = new MethodHistogram();

        Map<String, Snapshot> all = collector.snapshotsOfAll();
        for (Map.Entry<String, Snapshot> e : all.entrySet()) {
            mh.add(e.getKey(), e.getValue());
        }

        Map<String, Snapshot> failed = collector.snapshotsOfFailed();
        for (Map.Entry<String, Snapshot> e : failed.entrySet()) {
            mh.addFailed(e.getKey(), e.getValue().size());
        }

        messagePack().addMethod(mh);
    }
}
