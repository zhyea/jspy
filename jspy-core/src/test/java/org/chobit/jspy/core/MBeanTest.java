package org.chobit.jspy.core;


import org.chobit.jspy.core.gauge.ThreadsGaugeManager;
import org.chobit.jspy.core.model.ThreadInfo;

import java.util.List;

public class MBeanTest {

    public static void main(String[] args) throws InterruptedException {
        List<ThreadInfo> threads = ThreadsGaugeManager.allThreads();
        for (ThreadInfo ti : threads) {
            System.out.println("----------------------------------------------------------------");
            System.out.println(ti.getStackInfo());
        }
    }


}
