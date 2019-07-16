package org.chobit.jspy.service.beans;

import org.chobit.jspy.charts.AxisType;
import org.chobit.jspy.charts.ValueType;
import org.chobit.jspy.charts.annotation.Axis;
import org.chobit.jspy.charts.annotation.Series;

import java.util.Date;

public class ClassLoadingStat extends AbstractStatEntity {

    @Series(value = "已加载类总数", selected = false)
    private long totalLoaded;

    @Series("当前加载类总数")
    private long currentLoaded;

    @Series(value = "已卸载类总数", selected = false)
    private long unloaded;

    @Axis(type = AxisType.time, valueType = ValueType.MILLS_TIME)
    private Date eventTime;

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

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}
