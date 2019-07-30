package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.metrics.Snapshot;
import org.chobit.jspy.core.support.JSpyWatcherCollector;
import org.chobit.jspy.model.Histogram;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class MethodHistogramJobCapsule extends JobCapsule<List<Histogram>> {


    public MethodHistogramJobCapsule(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/api/method/receive";
    }


    @Override
    String name() {
        return "method";
    }

    @Override
    int intervalSeconds() {
        return config.getWatcherHistogramPeriodSeconds();
    }

    @Override
    public List<Histogram> collect() {
        JSpyWatcherCollector collector = JSpyWatcherCollector.getIfExists();
        if (null == collector || 0 == collector.size()) {
            return null;
        }

        List<Histogram> list = new LinkedList<>();

        Map<String, Snapshot> map = collector.snapshots();
        for (Map.Entry<String, Snapshot> e : map.entrySet()) {
            list.add(new Histogram(e.getKey(), e.getValue()));
        }

        return list;
    }
}
