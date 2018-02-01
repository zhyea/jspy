package org.chobit.jspy.sample.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

public class ThreadMXBeanTest {


    public static void main(String[] args) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        System.out.println(bean.findDeadlockedThreads());
        System.out.println(Arrays.toString(bean.getAllThreadIds()));

        ThreadInfo info = bean.getThreadInfo(2);
        System.out.println(info.getThreadName());
        StackTraceElement[] eles = info.getStackTrace();
        for (StackTraceElement e : eles) {
            System.out.println(e.toString());
        }
    }

}
