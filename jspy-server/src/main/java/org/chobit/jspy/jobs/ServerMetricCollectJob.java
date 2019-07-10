package org.chobit.jspy.jobs;


import org.chobit.jspy.service.MemoryUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServerMetricCollectJob {

    @Autowired
    private MemoryUsageService memoryService;


    @Scheduled(fixedRate = 6 * 1000)
    public void collectMemoryData() {
    }


}
