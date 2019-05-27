package org.chobit.jspy.service.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.chobit.jspy.charts.AxisType;
import org.chobit.jspy.charts.annotation.Axis;
import org.chobit.jspy.charts.annotation.Series;

import java.util.Date;

public class Memory extends AbstractEntity {

    @JsonIgnore
    private String appId;

    @JsonIgnore
    private String type;

    @Series("初始内存")
    private long init;

    @Series("已使用")
    private long used;

    @Series("已提交")
    private long committed;

    @Series("最大可用")
    private long max;

    @Axis(type = AxisType.MILLS_TIME)
    private Date eventTime;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
