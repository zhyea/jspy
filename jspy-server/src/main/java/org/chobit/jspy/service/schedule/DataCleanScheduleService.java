package org.chobit.jspy.service.schedule;


import org.chobit.jspy.service.*;
import org.chobit.jspy.service.common.HistogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCleanScheduleService {

    @Autowired
    private ClassLoadingService classLoadingService;
    @Autowired
    private CpuService cpuService;
    @Autowired
    private GcService gcService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private ThreadService threadService;
    @Autowired
    private HistogramService histogramService;
    @Autowired
    private MethodService methodService;
    @Autowired
    private MetricTargetService metricTargetService;


    public void clean() {
        classLoadingService.delete();
        cpuService.delete();
        gcService.delete();
        memoryService.delete();
        threadService.delete();
        histogramService.delete();
        methodService.delete();
        metricTargetService.delete();
    }

}
