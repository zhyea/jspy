package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.metrics.Snapshot;
import org.chobit.jspy.core.support.JSpyWatcherCollector;
import org.chobit.jspy.model.MethodHistogram;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class MethodHistogramJobCapsule extends JobCapsule<List<MethodHistogram>> {


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
    public List<MethodHistogram> collect() {
        JSpyWatcherCollector collector = JSpyWatcherCollector.getIfExists();
        if (null == collector || 0 == collector.size()) {
            return null;
        }

        List<MethodHistogram> list = new LinkedList<>();

        Map<String, Snapshot> map = collector.snapshots();
        for (Map.Entry<String, Snapshot> e : map.entrySet()) {
            MethodHistogram histogram = new MethodHistogram();

            histogram.setMethodId(e.getKey());
            Snapshot s = e.getValue();
            histogram.setStdDev((long) s.getStdDev());
            histogram.setMin(s.getMin());
            histogram.setMax(s.getMax());
            histogram.setMean((long) s.getMean());
            histogram.setPercent999((long) s.get999thPercentile());
            histogram.setPercent98((long) s.get98thPercentile());
            histogram.setPercent95((long) s.get95thPercentile());
            histogram.setPercent90((long) s.get90thPercentile());
            histogram.setPercent75((long) s.get75thPercentile());
            histogram.setMedian((long) s.getMedian());

            list.add(histogram);
        }

        return list;
    }
}
