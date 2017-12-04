package com.zhyea.jspy.sample.mxbean;

import junit.framework.TestCase;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class MemoryMxBeanTest extends TestCase {

    private MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();

    public void testMxBean(){
        System.out.println(mxBean.isVerbose());
        System.out.println(mxBean.getObjectName());
        System.out.println(mxBean.getHeapMemoryUsage());
        System.out.println(mxBean.getNonHeapMemoryUsage());
        System.out.println(mxBean.getObjectPendingFinalizationCount());
    }


}
