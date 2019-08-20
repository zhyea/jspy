package org.chobit.jspy.model;

import org.chobit.jspy.utils.SysTime;

public class ThreadCount {


    private long current;

    private long peak;

    private long totalStarted;

    private long daemon;

    private long eventTime = SysTime.millis();


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

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }
}
