package org.chobit.jspy.core.gauge;

import org.chobit.jspy.core.model.MemoryPool;

import java.lang.management.MemoryUsage;
import java.lang.management.*;
import java.util.LinkedList;
import java.util.List;

import static java.lang.management.MemoryType.HEAP;

public abstract class MemoryGaugeManager {


    private static final MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();

    private static List<MemoryPoolMXBean> poolMXBeans = ManagementFactory.getMemoryPoolMXBeans();


    public static MemoryUsage heapMemoryUsage() {
        return mxBean.getHeapMemoryUsage();
    }


    public static MemoryUsage nonHeapMemoryUsage() {
        return mxBean.getNonHeapMemoryUsage();
    }

    public static List<MemoryPool> memoryPools() {
        List<MemoryPool> result = new LinkedList<>();
        for (MemoryPoolMXBean mxBean : poolMXBeans) {
            MemoryType type = mxBean.getType();
            String name = "内存池-" + (type == HEAP ? "H" : "N") + "-" + mxBean.getName();
            String[] managers = mxBean.getMemoryManagerNames();
            MemoryUsage usage = mxBean.getUsage();
            MemoryUsage peakUsage = mxBean.getPeakUsage();

            MemoryPool pool = new MemoryPool(name, type, managers, usage, peakUsage);
            result.add(pool);
        }
        return result;
    }


    private MemoryGaugeManager() {
    }
}
