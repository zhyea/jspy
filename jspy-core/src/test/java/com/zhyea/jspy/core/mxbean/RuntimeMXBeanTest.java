package com.zhyea.jspy.core.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RuntimeMXBeanTest {

    public static void main(String[] args) {

        RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(mxBean.getStartTime());
        System.out.println(mxBean.getUptime());
        System.out.println(mxBean.getName());
        System.out.println(mxBean.getSpecName());
        System.out.println(mxBean.getVmName());
        System.out.println(mxBean.getVmVendor());
        System.out.println(mxBean.getVmVersion());
        System.out.println(mxBean.isBootClassPathSupported());
//        System.out.println(mxBean.getBootClassPath());
//        System.out.println(mxBean.getClassPath());
        System.out.println(mxBean.getInputArguments());
        System.out.println(mxBean.getObjectName());
//        System.out.println(mxBean.getSystemProperties());
//        System.out.println(mxBean.getLibraryPath());
//        System.out.println(mxBean.getManagementSpecVersion());


    }
}
