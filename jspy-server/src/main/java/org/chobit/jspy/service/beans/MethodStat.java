package org.chobit.jspy.service.beans;

import org.chobit.jspy.charts.AxisType;
import org.chobit.jspy.charts.ValueType;
import org.chobit.jspy.charts.annotation.Axis;

import java.util.Date;

public class MethodStat extends AbstractStatEntity {

    private String methodId;

    private long stdDev;

    private long min;

    private long max;

    private long mean;

    private long percentile999;

    private long percentile98;

    private long percentile95;

    private long percentile90;

    private long percentile75;

    private long median;

    @Axis(type = AxisType.time, valueType = ValueType.MILLS_TIME)
    private Date eventTime;

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

    public long getPercentile999() {
        return percentile999;
    }

    public void setPercentile999(long percentile999) {
        this.percentile999 = percentile999;
    }

    public long getPercentile98() {
        return percentile98;
    }

    public void setPercentile98(long percentile98) {
        this.percentile98 = percentile98;
    }

    public long getPercentile95() {
        return percentile95;
    }

    public void setPercentile95(long percentile95) {
        this.percentile95 = percentile95;
    }

    public long getPercentile90() {
        return percentile90;
    }

    public void setPercentile90(long percentile90) {
        this.percentile90 = percentile90;
    }

    public long getPercentile75() {
        return percentile75;
    }

    public void setPercentile75(long percentile75) {
        this.percentile75 = percentile75;
    }

    public long getMedian() {
        return median;
    }

    public void setMedian(long median) {
        this.median = median;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}
