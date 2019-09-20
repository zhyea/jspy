package org.chobit.jspy.jobs;


import org.chobit.jspy.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServerMetricCollectJob {

    @Autowired
    private MemoryService memoryService;


    @Scheduled(fixedRate = 6 * 1000)
    public void collectMemoryData() {
    }


}
