package org.chobit.jspy.sample.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.Arrays;
import java.util.List;

public class MemoryManagerMxBeansTest {


    public static void main(String[] args) {

        List<MemoryManagerMXBean> mxBeans = ManagementFactory.getMemoryManagerMXBeans();

        for (MemoryManagerMXBean mmm : mxBeans) {
            System.out.println(mmm.getName());
            System.out.println(mmm.getObjectName());
            System.out.println(Arrays.toString(mmm.getMemoryPoolNames()));
            System.out.println(mmm.isValid());

            System.out.println("-----------------------------------------------------------------------");
        }
    }
}
