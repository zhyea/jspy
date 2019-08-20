package org.chobit.jspy.service.entity;

import org.chobit.jspy.charts.XAxisType;
import org.chobit.jspy.charts.ValueType;
import org.chobit.jspy.charts.annotation.XAxis;
import org.chobit.jspy.charts.annotation.Series;

import java.util.Date;

import static org.chobit.jspy.charts.ValueType.STORAGE;

public class MemoryStat extends AbstractStatEntity {


    private String type;

    private String name;

    private String managerNames;

    @Series(value = "初始内存", valType = STORAGE, selected = false)
    private long init;

    @Series(value = "已使用", valType = STORAGE)
    private long used;

    @Series(value = "已提交", valType = STORAGE)
    private long committed;

    @Series(value = "最大可用", valType = STORAGE, selected = false)
    private long max;

    @XAxis(type = XAxisType.time, valueType = ValueType.MILLS_TIME)
    private Date eventTime;

    private int isPeak = 0;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerNames() {
        return managerNames;
    }

    public void setManagerNames(String managerNames) {
        this.managerNames = managerNames;
    }

    public long getInit() {
        return init;
    }

    public void setInit(long init) {
        this.init = init;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getCommitted() {
        return committed;
    }

    public void setCommitted(long committed) {
        this.committed = committed;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public int getIsPeak() {
        return isPeak;
    }

    public void setIsPeak(int isPeak) {
        this.isPeak = isPeak;
    }
}