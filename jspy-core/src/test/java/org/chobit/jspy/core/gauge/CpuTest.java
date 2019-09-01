package org.chobit.jspy.core.gauge;

import java.util.concurrent.TimeUnit;

public class CpuTest {


    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 10000; i++) {
            System.out.println(" ------ " + CPU.LOAD.value() + " ------ ");
            TimeUnit.SECONDS.sleep(1);
        }


    }

}
