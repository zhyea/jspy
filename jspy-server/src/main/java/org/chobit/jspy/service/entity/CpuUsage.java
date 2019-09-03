package org.chobit.jspy.service.entity;

import org.chobit.jspy.charts.annotation.Series;

import java.math.BigDecimal;

public class CpuUsage extends AbstractStatEntity {


    @Series(value = "CPU占用率", unit = "%")
    private BigDecimal usage;


    public BigDecimal getUsage() {
        return usage;
    }

    public void setUsage(BigDecimal usage) {
        this.usage = usage;
    }
}
