package com.zhyea.jspy.gauge;

public class ActiveThreadGauge implements Gauge {

    @Override
    public String name() {
        return "Active Thread";
    }

    @Override
    public double value() {
        return Thread.activeCount();
    }

}
