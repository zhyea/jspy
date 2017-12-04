package com.zhyea.jspy.sample.mxbean;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class BufferPoolMXBeanTest {

    public static void main(String[] args) {
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
