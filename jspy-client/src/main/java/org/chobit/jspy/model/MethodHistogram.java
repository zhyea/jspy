package org.chobit.jspy.model;

public class MethodHistogram {

    private String methodId;

    private long stdDev;

    private long min;

    private long max;

    private long mean;

    private long median;

    private long percent999;

    private long percent98;

    private long percent95;

    private long percent90;

    private long percent75;


    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public long getStdDev() {
        return stdDev;
    }

    public void setStdDev(long stdDev) {
        this.stdDev = stdDev;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getMean() {
        return mean;
    }

    public void setMean(long mean) {
        this.mean = mean;
    }

    public long getMedian() {
        return median;
    }

    public void setMedian(long median) {
        this.median = median;
    }

    public long getPercent999() {
        return percent999;
    }

    public void setPercent999(long percent999) {
        this.percent999 = percent999;
    }

    public long getPercent98() {
        return percent98;
    }

    public void setPercent98(long percent98) {
        this.percent98 = percent98;
    }

    public long getPercent95() {
        return percent95;
    }

    public void setPercent95(long percent95) {
        this.percent95 = percent95;
    }

    public long getPercent90() {
        return percent90;
    }

    public void setPercent90(long percent90) {
        this.percent90 = percent90;
    }

    public long getPercent75() {
        return percent75;
    }

    public void setPercent75(long percent75) {
        this.percent75 = percent75;
    }
}
