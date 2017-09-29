package com.zhyea.jspy.gauge;

public class UsedNonHeapMemoryGauge extends AbstractMemoryGauge {

    @Override
    public String name() {
        return "Used Non Heap Memory";
    }

    @Override
    public double value() {
        return MEMORY_MX_BEAN.getNonHeapMemoryUsage().getUsed();
    }

}
