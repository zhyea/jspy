package com.zhyea.jspy.core.gauge;

import com.zhyea.jspy.core.model.GarbageCollector;

import java.util.List;

public class GarbageCollectorGauge implements Gauge {
    @Override
    public String name() {
        return "Garbage Collector";
    }

    @Override
    public List<GarbageCollector> value() {
        return null;
    }


    /*
    * public final synchronized void update() {
        clean();
        List<GarbageCollectorMXBean> list = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : list) {
            String name = bean.getName();
            if (Arrays.binarySearch(OLD_GEN_COLLECTOR_NAMES, name) >= 0) {
                oldGCCount.getAndAdd(bean.getCollectionCount());
                oldGCTimes.getAndAdd(bean.getCollectionTime());
            } else if (Arrays.binarySearch(YOUNG_GEN_COLLECTOR_NAMES, name) >= 0) {
                youngGCCount.getAndAdd(bean.getCollectionCount());
                youngGCTimes.getAndAdd(bean.getCollectionTime());
            } else {
                System.out.println("Find unregister collector:" + name);
            }
        }
        lastCountTime = System.currentTimeMillis();
    }
    * */
}
