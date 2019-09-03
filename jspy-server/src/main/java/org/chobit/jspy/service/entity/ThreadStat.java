package org.chobit.jspy.service.entity;

import org.chobit.jspy.charts.annotation.Series;
import org.chobit.jspy.model.ThreadCount;
import org.chobit.jspy.utils.SysTime;

public class ThreadStat extends AbstractStatEntity {


    @Series("活跃线程数")
    private long current;

    @Series(value = "活跃线程峰值", selected = false)
    private long peak;

    @Series(value = "启动线程总数", selected = false)
    private long totalStarted;

    @Series("后台线程数")
    private long daemon;


    public ThreadStat() {
    }


    public ThreadStat(String appCode, String ip, ThreadCount count){
        this.setAppCode(appCode);
        this.setIp(ip);
        this.setCurrent(count.getCurrent());
        this.setPeak(count.getPeak());
        this.setTotalStarted(count.getTotalStarted());
        this.setDaemon(count.getDaemon());
        this.setEventTime(count.getEventTime() > 0 ? count.getEventTime() : SysTime.millis());
    }


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
