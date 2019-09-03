package org.chobit.jspy.spring;


import org.chobit.jspy.service.schedule.DataCleanScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleConfig {


    private static Logger logger = LoggerFactory.getLogger(ScheduleConfig.class);


    @Autowired
    private DataCleanScheduleService dataCleanService;


    @Scheduled(cron = "0 0 2 * * ?")
    public void clean() {
        logger.info("executing data clean job");
        dataCleanService.clean();
    }

}
