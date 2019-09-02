package org.chobit.jspy.service.entity;

import org.chobit.jspy.charts.ValueType;
import org.chobit.jspy.charts.XAxisType;
import org.chobit.jspy.charts.annotation.Series;
import org.chobit.jspy.charts.annotation.XAxis;

import java.math.BigDecimal;
import java.util.Date;

public class CpuUsage extends AbstractStatEntity {


    @Series(value = "CPU占用率", unit = "%")
    private BigDecimal usage;

    @XAxis(type = XAxisType.time, valueType = ValueType.MILLS_TIME)
    private Date eventTime;


    public BigDecimal getUsage() {
        return usage;
    }

    public void setUsage(BigDecimal usage) {
        this.usage = usage;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}
