package com.zhyea.jspy.gauge;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public abstract class AbstractMemoryGauge implements Gauge {

    protected static final MemoryMXBean MEMORY_MX_BEAN = ManagementFactory.getMemoryMXBean();

}
