package org.chobit.jspy.model;

import org.chobit.jspy.core.metrics.Snapshot;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MethodHistogram {

    private List<Histogram> histograms = new LinkedList<>();

    private Map<String, Long> failedCount = new LinkedHashMap<>(32);


    public void add(String name, Snapshot snapshot) {
        this.histograms.add(new Histogram(name, snapshot));
    }

    public void addFailed(String name, long count) {
        this.failedCount.put(name, count);
    }

    public boolean isEmpty() {
        return histograms.isEmpty();
    }

    public int size() {
        return histograms.size();
    }

    public long failedCount(String methodName) {
        return failedCount.getOrDefault(methodName, 0L);
    }


    public List<Histogram> getHistograms() {
        return histograms;
    }

    public Map<String, Long> getFailedCount() {
        return failedCount;
    }
}
