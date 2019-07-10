package org.chobit.jspy.service.beans;

public class ThreadStat extends AbstractStatEntity {

    private long current;

    private long peak;

    private long totalStarted;

    private long daemon;


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
}
