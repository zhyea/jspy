package com.zhyea.jspy.sample.mxbean;

import junit.framework.TestCase;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class OperatingSystemMXBeanTest extends TestCase {


    public void testMxBean() {
        OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(bean.getName());
        System.out.println(bean.getVersion());
        System.out.println(bean.getArch());
        System.out.println(bean.getAvailableProcessors());
        System.out.println(bean.getSystemLoadAverage());
    }

}
