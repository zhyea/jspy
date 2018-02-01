package org.chobit.jspy.sample.mxbean;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;

public class GarbageCollectorMxBeanTest {

    public static void main(String[] args) {
        List<GarbageCollectorMXBean> mxBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcm : mxBeans) {
            System.out.println(gcm.getName());
            System.out.println(gcm.getObjectName());
            System.out.println(Arrays.toString(gcm.getMemoryPoolNames()));
            System.out.println(gcm.getCollectionTime());
            System.out.println(gcm.getCollectionCount());

            System.out.println("-------------------------------------------------------");
        }
    }


}
