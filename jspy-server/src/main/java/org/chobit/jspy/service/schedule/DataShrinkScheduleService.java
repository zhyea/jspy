package org.chobit.jspy.service.schedule;

import org.chobit.jspy.service.ClassLoadingService;
import org.chobit.jspy.service.CpuService;
import org.chobit.jspy.service.MemoryService;
import org.chobit.jspy.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataShrinkScheduleService {

    @Autowired
    public ClassLoadingService classLoadingService;
    @Autowired
    public CpuService cpuService;
    @Autowired
    public MemoryService memoryService;
    @Autowired
    public ThreadService threadService;


    public void shrink() {
        classLoadingService.shrink();
        cpuService.shrink();
        memoryService.shrink();
        threadService.shrink();
    }
}
