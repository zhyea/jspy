package org.chobit.jspy.core.gauge;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

abstract class MemoryGaugeManager {


    private static final MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();


    static MemoryUsage heapMemoryUsage() {
        return mxBean.getHeapMemoryUsage();
    }


    static MemoryUsage nonHeapMemoryUsage() {
        return mxBean.getNonHeapMemoryUsage();
    }

}
