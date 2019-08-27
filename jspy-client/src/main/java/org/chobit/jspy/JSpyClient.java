package org.chobit.jspy;

import org.chobit.jspy.core.exceptions.JSpyException;
import org.chobit.jspy.jobs.AbstractOneOffJob;
import org.chobit.jspy.jobs.AbstractQuartzJob;
import org.chobit.jspy.jobs.internal.QuartzJobFactory;
import org.chobit.jspy.jobs.internal.JSpyJobRegistry;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public final class JSpyClient {

    private final JSpyJobRegistry jobRegistry;

    private final Scheduler scheduler;

    private final int startDelayedSeconds;

    JSpyClient(JSpyConfig config) {
        this.jobRegistry = new JSpyJobRegistry(config);
        this.scheduler = newScheduler();
        this.startDelayedSeconds = config.getStartDelayedSeconds();
    }


    public void start() {
        try {
            TimeUnit.SECONDS.sleep(startDelayedSeconds);
            startQuartzJob();
        } catch (Exception e) {
            throw new JSpyException(e);
        }
    }


    public void shutdownNow() {
        shutdown(false);
    }


    public void shutdown(boolean waitForJobsToComplete) {
        try {
            scheduler.shutdown(waitForJobsToComplete);
        } catch (SchedulerException e) {
            throw new JSpyException(e);
        }
    }


    private void startOneOffJob() {
        Iterable<AbstractOneOffJob> jobs = jobRegistry.oneOffJobs();
        for (AbstractOneOffJob job : jobs) {
            job.execute();
        }
    }

    private void startQuartzJob() throws SchedulerException {
        scheduler.start();
        Iterable<AbstractQuartzJob> jobs = jobRegistry.quartzJobs();
        for (AbstractQuartzJob j : jobs) {
            scheduler.scheduleJob(j.job(), j.trigger());
        }
    }


    private Scheduler newScheduler() {
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.setJobFactory(new QuartzJobFactory(jobRegistry));
            return scheduler;
        } catch (SchedulerException e) {
            throw new JSpyException(e);
        }
    }

}
