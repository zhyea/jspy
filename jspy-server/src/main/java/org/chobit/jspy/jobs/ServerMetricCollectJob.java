package org.chobit.jspy.jobs;


import org.chobit.jspy.core.gauge.MemoryGaugeManager;
import org.chobit.jspy.service.MemoryUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;

@Component
public class ServerMetricCollectJob {

    @Autowired
    private MemoryUsageService memoryService;


    @Scheduled(fixedRate = 6 * 1000)
    public void collectMemoryData() {
        MemoryUsage usage = MemoryGaugeManager.heapMemoryUsage();
        memoryService.insert(usage, "jspyServer", MemoryType.HEAP, "堆内存使用量",  null);
        System.out.println("------------------------");
    }


}
