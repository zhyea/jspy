package org.chobit.jspy.service.entity;

import org.chobit.jspy.charts.annotation.Series;
import org.chobit.jspy.model.ClassLoadingCount;
import org.chobit.jspy.utils.SysTime;

public class ClassLoadingStat extends AbstractStatEntity {

    @Series(value = "已加载类总数", selected = false)
    private long totalLoaded;

    @Series("当前加载类总数")
    private long currentLoaded;

    @Series(value = "已卸载类总数", selected = false)
    private long unloaded;


    public ClassLoadingStat() {
    }


    public ClassLoadingStat(String appCode, String ip, ClassLoadingCount count) {
        this.setAppCode(appCode);
        this.setIp(ip);
        this.setTotalLoaded(count.getTotalLoaded());
        this.setCurrentLoaded(count.getCurrentLoaded());
        this.setUnloaded(count.getUnloaded());
        this.setEventTime(count.getEventTime() > 0 ? count.getEventTime() : SysTime.millis());
    }

    public long getTotalLoaded() {
        return totalLoaded;
    }

    public void setTotalLoaded(long totalLoaded) {
        this.totalLoaded = totalLoaded;
    }

    public long getCurrentLoaded() {
        return currentLoaded;
    }

    public void setCurrentLoaded(long currentLoaded) {
        this.currentLoaded = currentLoaded;
    }

    public long getUnloaded() {
        return unloaded;
    }

    public void setUnloaded(long unloaded) {
        this.unloaded = unloaded;
    }
}
