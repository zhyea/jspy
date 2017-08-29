package com.zhyea.jspy.stats;

import com.zhyea.jspy.model.BufferPool;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class BufferPoolStat {

    public List<BufferPool> get() {
        List<BufferPoolMXBean> bufferPoolMXBeans = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
        List<BufferPool> bufferPools = new ArrayList<>(2);
        for (BufferPoolMXBean mxBean : bufferPoolMXBeans) {
            BufferPool bufferPool = new BufferPool();
            bufferPool.setName(mxBean.getName());
            bufferPool.setCount(mxBean.getCount());
            bufferPool.setMemoryUsed(mxBean.getMemoryUsed());
            bufferPool.setTotalCapacity(mxBean.getTotalCapacity());
        }
        return bufferPools;
    }

}
