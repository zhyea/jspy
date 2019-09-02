package org.chobit.jspy.service;

import org.chobit.jspy.utils.SysTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class PreheatService {

    private static final Logger logger = LoggerFactory.getLogger(PreheatService.class);

    @Autowired
    private MemoryService memoryService;
    @Autowired
    private MethodService methodService;
    @Autowired
    private GcService gcService;

    private ExecutorService executors = Executors.newFixedThreadPool(8);

    private long lastPreheatTime = 0;

    public void preheat(String appCode) {
        if (0 == lastPreheatTime || (SysTime.millis() - lastPreheatTime) > TimeUnit.DAYS.toMillis(1)) {
            executors.submit(() -> preheat0(appCode));
        }
    }

    private void preheat0(String appCode) {
        try {
            // mem names
            memoryService.getHeapPoolNames(appCode);
            memoryService.getNonHeapPoolNames(appCode);

            // method names
            methodService.findMethods(appCode);
            // gc name
            gcService.findGcNames(appCode);

            lastPreheatTime = SysTime.millis();
        } catch (Exception e) {
            logger.error("Executing preheat for {} error", appCode, e);
        }
    }


}
