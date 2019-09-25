package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public abstract class AbstractQuartzJob extends  AbstractJob implements Job {


    protected final JSpyConfig config;

    public AbstractQuartzJob(JSpyConfig config) {
        this.config = config;
    }

    abstract int intervalSeconds();


    String group() {
        return config.getAppCode();
    }

    public final JobDetail job() {
        return newJob(this.getClass())
                .withIdentity(name(), group())
                .build();
    }


    public final Trigger trigger() {
        return newTrigger()
                .withIdentity(name(), group())
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(intervalSeconds()).repeatForever())
                .build();
    }


    @Override
    public void execute(JobExecutionContext context) {
        collect();
    }


}
