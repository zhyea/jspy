package com.zhyea.jspy.stats;

import com.zhyea.jspy.model.GarbageCollector;
import com.zhyea.jspy.model.GarbageCollectorOverview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GarbageCollectorTest {

    private static final String TAB = "\t";

    private static final GarbageCollectorStat stat = new GarbageCollectorStat();

    public static void main(String[] args) throws InterruptedException {

        List<byte[]> container = new LinkedList<>();
        long count = 0;
        while (true) {
            TimeUnit.MILLISECONDS.sleep(10);
            count++;

            container.add(new byte[1024 * 1024]);
            if (count % (1024) == 0) {
                container = new ArrayList<>();
            }

            if (0 != count % 64) {
                continue;
            }
            List<GarbageCollector> list = stat.get();
            GarbageCollectorOverview overview = stat.getOverview(list);

            StringBuffer buffer = new StringBuffer();
            buffer.append(overview.getYoungCollectorCount()).append(TAB)
                    .append(overview.getYoungCollectTime()).append(TAB)
                    .append(overview.getOldCollectCount()).append(TAB)
                    .append(overview.getOldCollectTime());
            System.out.println(buffer);
        }
    }


}
