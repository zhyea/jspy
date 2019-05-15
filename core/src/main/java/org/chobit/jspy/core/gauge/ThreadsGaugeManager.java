package org.chobit.jspy.core.gauge;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

abstract class ThreadsGaugeManager {


    private static final ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

    static long threadCount() {
       return mxBean.getThreadCount();
    }


    static long peakThreadCount(){
        return mxBean.getPeakThreadCount();
    }


    static long totalStatedThreadCount(){
        return mxBean.getTotalStartedThreadCount();
    }


    static long daemonThreadCount() {
        return mxBean.getDaemonThreadCount();
    }


}
