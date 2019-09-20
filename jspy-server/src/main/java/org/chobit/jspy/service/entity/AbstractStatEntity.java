package org.chobit.jspy.service.entity;

import org.chobit.jspy.charts.ValueType;
import org.chobit.jspy.charts.XAxisType;
import org.chobit.jspy.charts.annotation.XAxis;

import java.util.Date;

public abstract class AbstractStatEntity extends AbstractEntity {

    private String appCode;

    private String ip;

    @XAxis(type = XAxisType.time, valueType = ValueType.MILLS_TIME)
    private Date eventTime;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventTime(long time) {
        this.eventTime = new Date(time);
    }
}
