package com.zhyea.jspy.core.gauge;

import com.zhyea.jspy.core.model.MemoryPool;

import java.lang.management.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.management.MemoryType.NON_HEAP;

public class MemoryGauge implements Gauge {


    @Override
    public String name() {
        return "Memory";
    }

    @Override
    public MemoryUsage value() {
        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();

        MemoryUsage heapMemory = mxBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemory = mxBean.getNonHeapMemoryUsage();

        long init = heapMemory.getInit() + nonHeapMemory.getInit();
        long used = heapMemory.getUsed() + nonHeapMemory.getUsed();
        long committed = heapMemory.getCommitted() + nonHeapMemory.getCommitted();
        long max = heapMemory.getMax() + nonHeapMemory.getMax();

        return new MemoryUsage(init, used, committed, max);
    }


    public List<MemoryPool> detail() {
        return detail(null);
    }


    protected List<MemoryPool> detail(MemoryType type) {
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        List<MemoryPool> list = new ArrayList<>(4);
        for (MemoryPoolMXBean p : pools) {
            if (null != type && NON_HEAP != p.getType()) {
                continue;
            }
            String name = p.getName();
            MemoryUsage usage = p.getUsage();

            MemoryPool mp = new MemoryPool(name, usage);

            mp.setMemoryManagerNames(p.getMemoryManagerNames());
            mp.setPeakUsage(p.getPeakUsage());

            mp.setUsageThresholdSupported(p.isUsageThresholdSupported());
            if (mp.isUsageThresholdSupported()) {
                mp.setUsageThreshold(p.getUsageThreshold());
                mp.setUsageThresholdCount(p.getUsageThresholdCount());
                mp.setUsageThresholdExceeded(p.isUsageThresholdExceeded());
            }

            mp.setCollectionUsageThresholdSupported(p.isCollectionUsageThresholdSupported());
            if (mp.isCollectionUsageThresholdSupported()) {
                mp.setCollectionUsageThreshold(p.getCollectionUsageThreshold());
                mp.setCollectionUsageThresholdCount(p.getCollectionUsageThresholdCount());
                mp.setCollectionUsageThresholdExceeded(p.isCollectionUsageThresholdExceeded());
            }

            list.add(mp);
        }
        return list;
    }


}
