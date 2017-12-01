package com.zhyea.jspy.core.gauge;

public interface Gauge {

    String name();

    <T> T value();

}
