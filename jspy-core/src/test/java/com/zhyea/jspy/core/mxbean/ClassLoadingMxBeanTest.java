package com.zhyea.jspy.core.mxbean;

import junit.framework.TestCase;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

public class ClassLoadingMxBeanTest extends TestCase {

    ClassLoadingMXBean mxBean = ManagementFactory.getClassLoadingMXBean();

    public void testInfo(){

        System.out.println(mxBean.getObjectName());
        System.out.println(mxBean.getLoadedClassCount());
        System.out.println(mxBean.getUnloadedClassCount());
        System.out.println(mxBean.getTotalLoadedClassCount());

        System.out.println("-----------------------------------------------");

        mxBean.setVerbose(true);
        System.out.println(mxBean.getLoadedClassCount());
    }

}
