package com.zhyea.jspy.core.mxbean;

import junit.framework.TestCase;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.Arrays;
import java.util.List;

public class MemoryManagerMxBeansTest extends TestCase {

    private List<MemoryManagerMXBean> mxBeans = ManagementFactory.getMemoryManagerMXBeans();

    public void testMxBeans() {
        for (MemoryManagerMXBean mmm : mxBeans) {
            System.out.println(mmm.getName());
            System.out.println(mmm.getObjectName());
            System.out.println(Arrays.toString(mmm.getMemoryPoolNames()));
            System.out.println(mmm.isValid());

            System.out.println("-----------------------------------------------------------------------");
        }
    }
}
