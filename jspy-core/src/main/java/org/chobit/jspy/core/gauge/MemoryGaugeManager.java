package org.chobit.jspy.core.gauge;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public abstract class MemoryGaugeManager {


    private static final MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();


    public static MemoryUsage heapMemoryUsage() {
        return mxBean.getHeapMemoryUsage();
    }


    public static MemoryUsage nonHeapMemoryUsage() {
        return mxBean.getNonHeapMemoryUsage();
    }

}
