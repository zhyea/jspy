package com.zhyea.jspy.sample.mxbean;

import junit.framework.TestCase;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class BufferPoolMXBeanTest extends TestCase {

    public void testMxBean() {
        List<BufferPoolMXBean> beans = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);


        for (BufferPoolMXBean bean : beans) {
            System.out.println(bean.getObjectName());
            System.out.println(bean.getName());
            System.out.println(bean.getCount());
            System.out.println(bean.getTotalCapacity());
            System.out.println(bean.getMemoryUsed());

            System.out.println("------------------------------------------");
        }

    }

}
