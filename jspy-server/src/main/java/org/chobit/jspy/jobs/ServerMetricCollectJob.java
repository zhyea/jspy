package org.chobit.jspy.jobs;


import org.chobit.jspy.core.gauge.MemoryGaugeManager;
import org.chobit.jspy.service.MemoryUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryUsage;

@Component
public class ServerMetricCollectJob {

    @Autowired
    private MemoryUsageService memoryService;


    @Scheduled(fixedRate = 6 * 1000)
    public void collectMemoryData() {
        MemoryUsage usage = MemoryGaugeManager.heapMemoryUsage();
        //memoryService.insert(usage, JSPY_CONSOLE_SHORT_CODE, HEAP, MemoryNames.nameOf(HEAP), null, Net.LOCAL_HOST_IP.value(), new Date());
    }


}
