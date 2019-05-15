package org.chobit.jspy.core;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();

        new Thread("zz").start();
        System.out.println(bean.getTotalStartedThreadCount());
        TimeUnit.SECONDS.sleep(1L);
        System.out.println(bean.getThreadCount());
        System.out.println("-------------------------------------------------");

        new Thread("zz").start();
        System.out.println(bean.getTotalStartedThreadCount());
        TimeUnit.SECONDS.sleep(1L);
        System.out.println(bean.getThreadCount());
        System.out.println("-------------------------------------------------");

        new Thread("zz").start();
        System.out.println(bean.getTotalStartedThreadCount());
        TimeUnit.SECONDS.sleep(1L);
        System.out.println(bean.getThreadCount());
        System.out.println("-------------------------------------------------");

    }


}
