package org.chobit.jspy.core.gauge;

public interface Gauge {

    String name();

    <T> T value();

}
