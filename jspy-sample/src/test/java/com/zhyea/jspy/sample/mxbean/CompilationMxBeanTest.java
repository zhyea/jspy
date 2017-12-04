package com.zhyea.jspy.sample.mxbean;

import junit.framework.TestCase;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

public class CompilationMxBeanTest extends TestCase {

    CompilationMXBean mxBean = ManagementFactory.getCompilationMXBean();

    public void testMxBean(){
        System.out.println(mxBean.getName());
        System.out.println(mxBean.getObjectName());
        System.out.println(mxBean.isCompilationTimeMonitoringSupported());
        System.out.println(mxBean.getTotalCompilationTime());
    }

}
