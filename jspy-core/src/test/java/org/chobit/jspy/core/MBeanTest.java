package org.chobit.jspy.core;


import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

public class MBeanTest {

    public static void main(String[] args) throws InterruptedException {

        CompilationMXBean bean = ManagementFactory.getCompilationMXBean();

        System.out.println(bean.getName());
        System.out.println(bean.getTotalCompilationTime());
        System.out.println(bean.getObjectName());

    }


}
