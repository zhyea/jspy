package org.chobit.jspy.core.gauge;


import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

abstract class GCGaugeManager {


    private static final List<GarbageCollectorMXBean> mxBeanList = ManagementFactory.getGarbageCollectorMXBeans();


    static long count() {
        return mxBeanList
                .stream()
                .mapToLong(GarbageCollectorMXBean::getCollectionCount)
                .sum();
    }


    static long usedTime() {
        return mxBeanList
                .stream()
                .mapToLong(GarbageCollectorMXBean::getCollectionTime)
                .sum();
    }


    private GCGaugeManager() {
    }

}
