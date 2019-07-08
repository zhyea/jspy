package org.chobit.jspy.core.support;

import org.chobit.jspy.core.metrics.Histogram;
import org.chobit.jspy.core.metrics.SlidingTimeWindowReservoir;
import org.chobit.jspy.core.metrics.Snapshot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public final class JSpyWatcherCollector {

    private final long collectPeriodSeconds;

    private final ConcurrentHashMap<String, Histogram> methodHistogramMap;

    public JSpyWatcherCollector(long collectPeriodSeconds,
                                ConcurrentHashMap<String, Histogram> methodHistogram) {
        this.collectPeriodSeconds = collectPeriodSeconds;
        this.methodHistogramMap = methodHistogram;
    }


    public void updateHistogram(String methodId, long duration) {
        Histogram histogram = methodHistogramMap.get(methodId);
        if (null == histogram) {
            histogram = newHistogram();
            Histogram old = methodHistogramMap.putIfAbsent(methodId, histogram);
            if (null != old) {
                histogram = old;
            }
            histogram.update(duration);
        }
    }


    public Map<String, Snapshot> snapshots() {
        Map<String, Snapshot> map = new HashMap<>(size());
        for (Map.Entry<String, Histogram> entry : methodHistogramMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getSnapshot());
        }
        return Collections.unmodifiableMap(map);
    }


    public int size() {
        return methodHistogramMap.size();
    }

    private Histogram newHistogram() {
        SlidingTimeWindowReservoir reservoir =
                new SlidingTimeWindowReservoir(collectPeriodSeconds, TimeUnit.SECONDS);
        return new Histogram(reservoir);
    }
}
