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
    private final ConcurrentHashMap<String, Histogram> failedMethodHistogramMap;

    private JSpyWatcherCollector(long collectPeriodSeconds, int initialCapacity) {
        this.collectPeriodSeconds = collectPeriodSeconds;
        this.methodHistogramMap = new ConcurrentHashMap<>(initialCapacity);
        this.failedMethodHistogramMap = new ConcurrentHashMap<>(initialCapacity);
    }

    /**
     * 更新方法Histogram
     */
    public void update(boolean failed, String methodId, long duration) {
        if (failed) {
            update(methodId, duration);
        } else {
            updateFailed(methodId, duration);
        }
    }

    /**
     * 更新方法Histogram
     */
    public void update(String methodId, long duration) {
        Histogram histogram = getHistogram(this.methodHistogramMap, methodId);
        histogram.update(duration);
    }

    /**
     * 更新执行出现异常的方法的Histogram
     */
    public void updateFailed(String methodId, long duration) {
        Histogram histogram = getHistogram(this.failedMethodHistogramMap, methodId);
        histogram.update(duration);
    }


    public Map<String, Snapshot> snapshotsOfAll() {
        return snapshotsOfMap(this.methodHistogramMap);
    }


    public Map<String, Snapshot> snapshotsOfFailed() {
        return snapshotsOfMap(this.failedMethodHistogramMap);
    }


    private synchronized Map<String, Snapshot> snapshotsOfMap(ConcurrentHashMap<String, Histogram> methodHistogramMap) {
        Map<String, Snapshot> map = new HashMap<>(methodHistogramMap.size() + 2);
        for (Map.Entry<String, Histogram> entry : methodHistogramMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getSnapshot());
        }
        return Collections.unmodifiableMap(map);
    }


    public int size() {
        return methodHistogramMap.size();
    }


    private Histogram getHistogram(ConcurrentHashMap<String, Histogram> methodHistogramMap,
                                   String methodId) {
        Histogram histogram = methodHistogramMap.get(methodId);
        if (null == histogram) {
            histogram = newHistogram();
            Histogram old = methodHistogramMap.putIfAbsent(methodId, histogram);
            if (null != old) {
                histogram = old;
            }
        }
        return histogram;
    }


    private Histogram newHistogram() {
        SlidingTimeWindowReservoir reservoir =
                new SlidingTimeWindowReservoir(collectPeriodSeconds, TimeUnit.SECONDS);
        return new Histogram(reservoir);
    }


    private static Builder builder = new Builder();


    /**
     * 如果JVM中不存在JSpyWatcherCollector实例，则创建一个；如已存在，则直接返回
     */
    public static JSpyWatcherCollector createIfNon(int histogramPeriod, int expectNumOfMethods) {
        return builder.histogramPeriod(histogramPeriod)
                .expectMethods(expectNumOfMethods)
                .build();
    }


    /**
     * 尝试获取JVM中的Collector实例
     */
    public static JSpyWatcherCollector getIfExists() {
        return builder.collector;
    }


    private static final class Builder {

        private JSpyWatcherCollector collector = null;

        private int period = 60 * 5;

        private int expectMethods = 32;

        public Builder histogramPeriod(int periodInSeconds) {
            this.period = periodInSeconds;
            return this;
        }

        public Builder expectMethods(int expectMethods) {
            this.expectMethods = expectMethods;
            return this;
        }


        public JSpyWatcherCollector build() {
            if (null != collector) {
                return collector;
            }
            collector = new JSpyWatcherCollector(period, expectMethods);
            return collector;
        }
    }
}
