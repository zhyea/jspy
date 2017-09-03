package com.zhyea.jspy.stats;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GCStatsTest extends TestCase {


    public static void main(String[] args) throws InterruptedException {

        GCStat stat = new GCStat(10);

        List<byte[]> container = new ArrayList<>();
        long count = 1;

        while (true) {
            container.add(new byte[1024]);
            if (count % (1000 * 1000) == 0) {
                container = new ArrayList<>();
            }
            if (count % 100 == 0) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(stat.getYoungGCCount()).append("\t")
                        .append(stat.getYoungGCTimesInMills()).append("\t")
                        .append(stat.getFullGCCount()).append("\t")
                        .append(stat.getFullGCTimesInMills());
                System.out.println(buffer.toString());
            }
            TimeUnit.MILLISECONDS.sleep(10);
            count++;
        }
    }

}
