package com.zhyea.jspy.sample.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class MemoryMxBeanTest {

    public static void main(String[] args) {
        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();

        System.out.println(mxBean.isVerbose());
        System.out.println(mxBean.getObjectName());
        System.out.println(mxBean.getHeapMemoryUsage());
        System.out.println(mxBean.getNonHeapMemoryUsage());
        System.out.println(mxBean.getObjectPendingFinalizationCount());
    }


}
