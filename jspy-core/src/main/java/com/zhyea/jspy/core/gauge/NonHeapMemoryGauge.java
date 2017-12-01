package com.zhyea.jspy.core.gauge;

import com.zhyea.jspy.core.model.MemoryPool;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.List;

public class NonHeapMemoryGauge extends MemoryGauge {

    @Override
    public String name() {
        return "Non-Heap Memory";
    }


    @Override
    public MemoryUsage value() {
        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
        return mxBean.getNonHeapMemoryUsage();
    }

    @Override
    public List<MemoryPool> detail() {
        return detail(MemoryType.NON_HEAP);
    }

}
