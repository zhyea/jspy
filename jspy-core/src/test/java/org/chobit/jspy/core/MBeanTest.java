package org.chobit.jspy.core;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class MBeanTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threads = bean.dumpAllThreads(true, true);

        for (ThreadInfo t : threads) {
            System.out.println(t.getThreadId() + " : " + t.getThreadState() + " : " + t.getThreadName() + " : " + bean.getThreadCpuTime(t.getThreadId()) + " : " + bean.getThreadUserTime(t.getThreadId()) );
        }

        TimeUnit.MINUTES.sleep(3L);

    }


}
