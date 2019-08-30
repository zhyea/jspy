package org.chobit.jspy;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class HelloJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getJobDetail().getKey());
    }


    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        scheduler.start();

        JobDetail detail = newJob(HelloJob.class).withIdentity("myJob", "group0").build();

        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group0")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();

        scheduler.scheduleJob(detail, trigger);

        System.out.println("end!!");
    }
}
