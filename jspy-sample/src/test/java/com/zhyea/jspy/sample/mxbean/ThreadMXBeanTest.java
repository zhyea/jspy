package com.zhyea.jspy.sample.mxbean;

import junit.framework.TestCase;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

public class ThreadMXBeanTest extends TestCase {


    public void testMxBean(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        System.out.println(bean.findDeadlockedThreads());
        System.out.println(Arrays.toString(bean.getAllThreadIds()));
    }


    public void testThreadInfo(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        ThreadInfo info = bean.getThreadInfo(2);
        System.out.println(info.getThreadName());
        StackTraceElement[] eles = info.getStackTrace();
        for(StackTraceElement e : eles){
            System.out.println(e.toString());
        }
    }

}
