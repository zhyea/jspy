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
            String name = (type == HEAP ? "H" : "N") + "-" + mxBean.getName();
            String[] managers = mxBean.getMemoryManagerNames();
            MemoryUsage usage = mxBean.getUsage();
            MemoryUsage peakUsage = mxBean.getPeakUsage();

            MemoryPool pool = new MemoryPool(name, type, managers, usage, peakUsage);
            result.add(pool);
        }
        return result;
    }


    /**
     * 比较内存使用量
     *
     * @param target 目标数据
     * @param other  要比较的数据
     * @return 若目标数据的关键内存指标值小于要比较的数据，则返回false，否则返回true
     */
    public static boolean compareMemoryUsage(MemoryUsage target, MemoryUsage other) {
        if (target.getInit() < other.getInit()) {
            return false;
        }
        if (target.getCommitted() <= other.getCommitted()) {
            return false;
        }
        if (target.getUsed() <= other.getUsed()) {
            return false;
        }
        if (target.getMax() < other.getMax()) {
            return false;
        }
        return true;
    }


    private MemoryGaugeManager() {
    }
}
