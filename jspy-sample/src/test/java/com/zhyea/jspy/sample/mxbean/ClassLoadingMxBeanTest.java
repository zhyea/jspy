package com.zhyea.jspy.sample.mxbean;


import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

public class ClassLoadingMxBeanTest {


    public static void main(String[] args) {
        ClassLoadingMXBean mxBean = ManagementFactory.getClassLoadingMXBean();

        System.out.println(mxBean.getObjectName());
        System.out.println(mxBean.getLoadedClassCount());
        System.out.println(mxBean.getUnloadedClassCount());
        System.out.println(mxBean.getTotalLoadedClassCount());

        System.out.println("-----------------------------------------------");

        mxBean.setVerbose(true);
        System.out.println(mxBean.getLoadedClassCount());
    }

}
