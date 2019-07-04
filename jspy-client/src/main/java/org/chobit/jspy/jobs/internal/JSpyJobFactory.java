package org.chobit.jspy.jobs.internal;

import org.chobit.jspy.jobs.JobCapsule;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSpyJobFactory implements JobFactory {

    private JSpyJobProxy jobProxy;

    public JSpyJobFactory(JSpyJobProxy jobProxy) {
        this.jobProxy = jobProxy;
    }

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected Logger getLog() {
        return log;
    }

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();
        try {
            if (log.isDebugEnabled()) {
                log.debug(
                        "Producing instance of Job '{}', class={}", jobDetail.getKey(), jobClass.getName());
            }

            if (JobCapsule.class.isAssignableFrom(jobClass)) {
                return jobProxy.getInstance((Class<? extends JobCapsule>) jobClass);
            } else {
                return jobClass.newInstance();
            }
        } catch (Exception e) {
            SchedulerException se = new SchedulerException("Problem instantiating class '" + jobDetail.getJobClass().getName() + "'", e);
            log.error("Problem instantiating class '{}'", jobDetail.getJobClass().getName(), e);
            throw se;
        }
    }

}
