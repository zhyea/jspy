package org.chobit.jspy.jobs;


import org.chobit.jspy.core.gauge.MemoryGaugeManager;
import org.chobit.jspy.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryUsage;

@Component
public class ServerMetricCollectJob {

    @Autowired
    private MemoryService memoryService;


    public void collectMemoryData(){
        MemoryUsage useage = MemoryGaugeManager.heapMemoryUsage();

    }


}
