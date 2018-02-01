package org.chobit.jspy.core.gauge;

import org.chobit.jspy.core.model.MemoryPool;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.List;

public class HeapMemoryGauge extends MemoryGauge {

    @Override
    public String name() {
        return "Heap Memory";
    }


    @Override
    public MemoryUsage value() {
        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
        return mxBean.getHeapMemoryUsage();
    }


    @Override
    public List<MemoryPool> detail() {
        return detail(MemoryType.HEAP);
    }

}
