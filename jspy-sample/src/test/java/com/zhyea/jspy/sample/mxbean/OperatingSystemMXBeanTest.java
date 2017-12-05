package com.zhyea.jspy.sample.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class OperatingSystemMXBeanTest {

    public static void main(String[] args) {
        OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(bean.getName());
        System.out.println(bean.getVersion());
        System.out.println(bean.getArch());
        System.out.println(bean.getAvailableProcessors());
        System.out.println(bean.getSystemLoadAverage());
    }

}
