package org.chobit.jspy.core.gauge;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class ThreadsGaugeManager {


    private static final ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

    static long threadCount() {
        return mxBean.getThreadCount();
    }


    static long peakThreadCount() {
        return mxBean.getPeakThreadCount();
    }


    static long totalStartedThreadCount() {
        return mxBean.getTotalStartedThreadCount();
    }


    static long daemonThreadCount() {
        return mxBean.getDaemonThreadCount();
    }


    public static List<org.chobit.jspy.core.model.ThreadInfo> allThreads() {
        ThreadInfo[] arr = mxBean.dumpAllThreads(true, true);
        List<org.chobit.jspy.core.model.ThreadInfo> list = new LinkedList<>();

        for (ThreadInfo t : arr) {
            org.chobit.jspy.core.model.ThreadInfo t0
                    = new org.chobit.jspy.core.model.ThreadInfo(t);

            long cpuTime = TimeUnit.NANOSECONDS.toMillis(mxBean.getThreadCpuTime(t0.getId()));
            long userTime = TimeUnit.NANOSECONDS.toMillis(mxBean.getThreadUserTime(t0.getId()));

            t0.setCpuTime(cpuTime);
            t0.setUserTime(userTime);

            list.add(t0);
        }

        return list;
    }


    private ThreadsGaugeManager() {
    }

}
