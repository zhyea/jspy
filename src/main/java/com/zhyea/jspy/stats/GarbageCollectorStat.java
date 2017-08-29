package com.zhyea.jspy.stats;

import com.zhyea.jspy.model.GarbageCollector;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class GarbageCollectorStat {

    public List<GarbageCollector> get() {
        List<GarbageCollectorMXBean> mxBeans = ManagementFactory.getGarbageCollectorMXBeans();
        List<GarbageCollector> garbageCollectors = new ArrayList<>(8);
        for (GarbageCollectorMXBean mxBean : mxBeans) {
            String name = mxBean.getName();
            boolean isValid = mxBean.isValid();
            String[] memoryPoolNames = mxBean.getMemoryPoolNames();
            long collectionCount = mxBean.getCollectionCount();
            long collectionTime = mxBean.getCollectionTime();
            garbageCollectors.add(new GarbageCollector(name, isValid, memoryPoolNames, collectionCount, collectionTime));
        }
        return garbageCollectors;
    }

}
