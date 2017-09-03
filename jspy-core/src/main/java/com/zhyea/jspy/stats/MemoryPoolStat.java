package com.zhyea.jspy.stats;

import com.zhyea.jspy.model.MemoryPool;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.ArrayList;
import java.util.List;

import static java.lang.management.MemoryType.HEAP;

public class MemoryPoolStat {


    public List<MemoryPool> get() {
        List<MemoryPoolMXBean> list = ManagementFactory.getMemoryPoolMXBeans();
        List<MemoryPool> memoryPools = new ArrayList<>(8);
        for (MemoryPoolMXBean mxBean : list) {
            memoryPools.add(build(mxBean));
        }
        return memoryPools;
    }


    private MemoryPool build(MemoryPoolMXBean mxBean) {
        String name = mxBean.getName();
        boolean isHeap = HEAP == mxBean.getType();
        boolean isValid = mxBean.isValid();
        boolean collectionThresholdSupported = mxBean.isCollectionUsageThresholdSupported();
        boolean usageThresholdSupported = mxBean.isUsageThresholdSupported();

        MemoryPool memoryPool = new MemoryPool(name, isHeap, isValid, collectionThresholdSupported, usageThresholdSupported);


        memoryPool.setMemoryManagerNames(mxBean.getMemoryManagerNames());
        memoryPool.setUsageThreshold(mxBean.getUsageThreshold());
        memoryPool.setCollectionThreshold(mxBean.getCollectionUsageThreshold());

        return memoryPool;
    }

}
