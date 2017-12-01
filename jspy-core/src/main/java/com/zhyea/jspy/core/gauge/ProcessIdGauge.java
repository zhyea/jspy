package com.zhyea.jspy.core.gauge;

public class ProcessIdGauge implements Gauge {

    @Override
    public String name() {
        return "Process ID";
    }

    @Override
    public String value() {
        return null;
    }
}
