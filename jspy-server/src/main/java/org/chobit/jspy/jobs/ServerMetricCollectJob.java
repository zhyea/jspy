package org.chobit.jspy.jobs;


import org.chobit.jspy.core.gauge.MemoryGaugeManager;
import org.chobit.jspy.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryUsage;

@Component
public class ServerMetricCollectJob {

    @Autowired
    private MemoryService memoryService;


    @Scheduled(fixedRate = 6 * 1000)
    public void collectMemoryData() {
        MemoryUsage usage = MemoryGaugeManager.heapMemoryUsage();
        memoryService.insert(usage, "jspyServer", "堆内存");
        System.out.println("------------------------");
    }


}
