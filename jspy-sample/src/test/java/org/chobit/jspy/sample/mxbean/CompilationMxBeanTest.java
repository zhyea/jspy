package org.chobit.jspy.sample.mxbean;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

public class CompilationMxBeanTest {


    public static void main(String[] args) {
        CompilationMXBean mxBean = ManagementFactory.getCompilationMXBean();
        System.out.println(mxBean.getName());
        System.out.println(mxBean.getObjectName());
        System.out.println(mxBean.isCompilationTimeMonitoringSupported());
        System.out.println(mxBean.getTotalCompilationTime());
    }

}
