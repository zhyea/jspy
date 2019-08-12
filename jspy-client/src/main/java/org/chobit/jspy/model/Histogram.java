package org.chobit.jspy.model;

import org.chobit.jspy.core.metrics.Snapshot;
import org.chobit.jspy.utils.SysTime;

public class Histogram {

    private String name;

    private long count;

    private long sum;

    private long stdDev;

    private long min;

    private long max;

    private long mean;

    private long median;

    private long percentile999;

    private long percentile98;

    private long percentile95;

    private long percentile90;

    private long percentile75;

    private long eventTime = SysTime.millis();


    public Histogram() {
    }


    public Histogram(String name, Snapshot snapshot) {
        this.name = name;

        this.count = snapshot.size();
        this.sum = snapshot.sum();
        this.stdDev = (long) snapshot.getStdDev();
        this.min = snapshot.getMin();
        this.max = snapshot.getMax();
        this.mean = (long) snapshot.getMean();
        this.percentile999 = (long) snapshot.get999thPercentile();
        this.percentile98 = (long) snapshot.get98thPercentile();
        this.percentile95 = (long) snapshot.get95thPercentile();
        this.percentile90 = (long) snapshot.get90thPercentile();
        this.percentile75 = (long) snapshot.get75thPercentile();
        this.median = (long) snapshot.getMedian();
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public long getSum() {
        return sum;
    }

    public long getStdDev() {
        return stdDev;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public long getMean() {
        return mean;
    }

    public long getMedian() {
        return median;
    }

    public long getPercentile999() {
        return percentile999;
    }

    public long getPercentile98() {
        return percentile98;
    }

    public long getPercentile95() {
        return percentile95;
    }

    public long getPercentile90() {
        return percentile90;
    }

    public long getPercentile75() {
        return percentile75;
    }

    public long getEventTime() {
        return eventTime;
    }
}
