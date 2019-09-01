package org.chobit.jspy.core;


import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class MBeanTest {

    public static void main(String[] args) throws InterruptedException {

        OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();

        for(int i=0; i<1000; i++){
            bean.getSystemLoadAverage();
        }
    }


}
