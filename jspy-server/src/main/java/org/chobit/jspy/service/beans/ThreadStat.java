package org.chobit.jspy.service.beans;

import org.chobit.jspy.charts.AxisType;
import org.chobit.jspy.charts.ValueType;
import org.chobit.jspy.charts.annotation.XAxis;
import org.chobit.jspy.charts.annotation.Series;

import java.util.Date;

public class ThreadStat extends AbstractStatEntity {


    @Series("活跃线程数")
    private long current;

    @Series(value = "活跃线程峰值", selected = false)
    private long peak;

    @Series(value = "启动线程总数", selected = false)
    private long totalStarted;

    @Series("后台线程数")
    private long daemon;

    @XAxis(type = AxisType.time, valueType = ValueType.MILLS_TIME)
    private Date eventTime;


    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getPeak() {
        return peak;
    }

    public void setPeak(long peak) {
        this.peak = peak;
    }

    public long getTotalStarted() {
        return totalStarted;
    }

    public void setTotalStarted(long totalStarted) {
        this.totalStarted = totalStarted;
    }

    public long getDaemon() {
        return daemon;
    }

    public void setDaemon(long daemon) {
        this.daemon = daemon;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}
