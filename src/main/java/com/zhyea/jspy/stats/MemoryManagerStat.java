package com.zhyea.jspy.stats;

import com.zhyea.jspy.model.MemoryManager;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.ArrayList;
import java.util.List;

public class MemoryManagerStat {


    public List<MemoryManager> get() {
        List<MemoryManagerMXBean> mxBeans = ManagementFactory.getMemoryManagerMXBeans();
        List<MemoryManager> memoryManagers = new ArrayList<>(8);
        for (MemoryManagerMXBean mxBean : mxBeans) {
            String name = mxBean.getName();
            boolean isValid = mxBean.isValid();
            String[] memoryPoolNames = mxBean.getMemoryPoolNames();
            memoryManagers.add(new MemoryManager(name, isValid, memoryPoolNames));
        }
        return memoryManagers;
    }

}
