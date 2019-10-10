package org.chobit.jspy.spring;


import org.chobit.jspy.service.schedule.DataCleanScheduleService;
import org.chobit.jspy.service.schedule.DataShrinkScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleConfig {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleConfig.class);


    @Autowired
    private DataCleanScheduleService dataCleanService;
    @Autowired
    private DataShrinkScheduleService dataShrinkService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void clean() {
        logger.info("------------>> executing data clean job");
        dataCleanService.clean();
        System.out.println("------------>> End executing data clean job");
    }


    @Scheduled(cron = "0 0 0/1 * * ?")
    public void shrink() {
        logger.info("------------>> executing data shrink job");
        dataShrinkService.shrink();
        System.out.println("------------>> End executing data shrink job");
    }


}
