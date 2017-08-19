package com.zhyea.jspy.gc;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GCStatsTest extends TestCase {


    public static void main(String[] args) throws InterruptedException {
        List<byte[]> container = new ArrayList<>();
        long count = 1;
        while (true) {
            container.add(new byte[1024]);
            if (count % (1000 * 1000) == 0) {
                container = new ArrayList<>();
            }
            if (count % 100 == 0) {
                System.out.println("full gc : " + GCStats.countFullGC());
                System.out.println("young gc : " + GCStats.countYoungGC());
                System.out.println("--------------------------------------------------------");
            }
            TimeUnit.MILLISECONDS.sleep(10);
            count++;
        }
    }

}
