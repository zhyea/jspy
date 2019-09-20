package org.chobit.jspy.jobs.internal;

import org.chobit.jspy.jobs.AbstractQuartzJob;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJobFactory implements JobFactory {

    private JSpyJobRegistry jobRegistry;

    public QuartzJobFactory(JSpyJobRegistry jobRegistry) {
        this.jobRegistry = jobRegistry;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();
        try {
            if (logger.isDebugEnabled()) {
                logger.debug(
                        "Producing instance of Job '{}', class={}", jobDetail.getKey(), jobClass.getName());
            }

            if (AbstractQuartzJob.class.isAssignableFrom(jobClass)) {
                return jobRegistry.getQuartzJobInstance((Class<? extends AbstractQuartzJob>) jobClass);
            }

            return jobClass.newInstance();
        } catch (Exception e) {
            SchedulerException se = new SchedulerException("Problem instantiating class '" + jobDetail.getJobClass().getName() + "'", e);
            logger.error("Problem instantiating class '{}'", jobDetail.getJobClass().getName(), e);
            throw se;
        }
    }

}
