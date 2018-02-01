package org.chobit.jspy.sample.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.Arrays;
import java.util.List;

public class MemoryPoolMXBeanTest {


    public static void main(String[] args) {
        List<MemoryPoolMXBean> beans = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean bean : beans) {
            System.out.println(bean.isValid());
            System.out.println(bean.getName());
            System.out.println(Arrays.toString(bean.getMemoryManagerNames()));


            System.out.println(bean.getType());
            System.out.println(bean.getUsage());
            System.out.println(bean.getPeakUsage());
            System.out.println(bean.getCollectionUsage());

            System.out.println(bean.isCollectionUsageThresholdSupported());
            if (bean.isCollectionUsageThresholdSupported()) {
                System.out.println("Collection Threshold: ");
                System.out.println("\t" + bean.getCollectionUsageThreshold());
                System.out.println("\t" + bean.getCollectionUsageThresholdCount());
                System.out.println("\t" + bean.isCollectionUsageThresholdExceeded());
            }

            System.out.println(bean.isUsageThresholdSupported());
            if (bean.isUsageThresholdSupported()) {
                System.out.println("Usage Threshold: ");
                System.out.println("\t" + bean.getUsageThreshold());
                System.out.println("\t" + bean.getUsageThresholdCount());
                System.out.println("\t" + bean.isUsageThresholdExceeded());
            }

            System.out.println("-----------------------------------------------------------------");
        }


    }


}
