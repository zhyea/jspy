package org.chobit.jspy.core.gauge;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

abstract class ClassLoadingGaugeManager {


    private static final ClassLoadingMXBean mxBean = ManagementFactory.getClassLoadingMXBean();


    static long totalLoadedClassCount() {
        return mxBean.getTotalLoadedClassCount();
    }


    static long currentLoadedClassCount() {
        return mxBean.getLoadedClassCount();
    }


    static long unloadedClassCount() {
        return mxBean.getUnloadedClassCount();
    }


    private ClassLoadingGaugeManager() {
    }

}
