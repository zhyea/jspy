package org.chobit.jspy.service.beans;

import org.chobit.jspy.charts.AxisType;
import org.chobit.jspy.charts.ValueType;
import org.chobit.jspy.charts.annotation.XAxis;
import org.chobit.jspy.charts.annotation.Series;
import org.chobit.jspy.constants.HistogramType;
import org.chobit.jspy.model.Histogram;

import java.util.Date;

public class HistogramEntity extends AbstractStatEntity {

    private int type;

    private String name;

    @Series(value = "次数", yAxisIndex = 1)
    private long count;

    @Series(value = "标准差", selected = false)
    private long stdDev;

    @Series(value = "Min", selected = false)
    private long min;

    @Series(value = "Max", selected = false)
    private long max;

    @Series(value = "Mean")
    private long mean;

    @Series(value = "99.9%", selected = false)
    private long percentile999;

    @Series(value = "98%", selected = false)
    private long percentile98;

    @Series(value = "95%", selected = false)
    private long percentile95;

    @Series(value = "90%", selected = false)
    private long percentile90;

    @Series(value = "75%", selected = false)
    private long percentile75;

    @Series(value = "中值", selected = false)
    private long median;

    @XAxis(type = AxisType.time, valueType = ValueType.MILLS_TIME)
    private Date eventTime;

    public HistogramEntity() {
    }

    public HistogramEntity(String appCode, String ip, HistogramType type, Histogram his) {
        setAppCode(appCode);
        setIp(ip);
        this.type = type.id;
        this.name = his.getName();
        this.count = his.getCount();
        this.stdDev = his.getStdDev();
        this.min = his.getMin();
        this.max = his.getMax();
        this.mean = his.getMean();
        this.percentile999 = his.getPercentile999();
        this.percentile98 = his.getPercentile98();
        this.percentile95 = his.getPercentile95();
        this.percentile90 = his.getPercentile90();
        this.percentile75 = his.getPercentile75();
        this.median = his.getMedian();
        this.eventTime = new Date(his.getEventTime());
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
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
