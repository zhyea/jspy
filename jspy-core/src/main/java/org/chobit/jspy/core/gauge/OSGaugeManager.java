package org.chobit.jspy.core.gauge;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

abstract class OSGaugeManager {


    private static OperatingSystemMXBean mxBean = ManagementFactory.getOperatingSystemMXBean();


    static String arch() {
        return mxBean.getArch();
    }

    static String osName() {
        return mxBean.getName();
    }


    static String osVersion() {
        return mxBean.getVersion();
    }


    static int availableProcessors() {
        return mxBean.getAvailableProcessors();
    }


    static double loadAverage() {
        return mxBean.getSystemLoadAverage();
    }

}
