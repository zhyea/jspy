package com.zhyea.jspy.gauge;

public class UsedHeapMemoryGauge extends AbstractMemoryGauge {


    @Override
    public String name() {
        return "Used Heap Memory";
    }

    @Override
    public double value() {
        return MEMORY_MX_BEAN.getHeapMemoryUsage().getUsed();
    }
}
