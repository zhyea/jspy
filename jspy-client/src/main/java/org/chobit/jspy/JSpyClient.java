package org.chobit.jspy;

import org.chobit.jspy.core.exceptions.JSpyException;
import org.chobit.jspy.jobs.JobCapsule;
import org.chobit.jspy.jobs.internal.JSpyJobFactory;
import org.chobit.jspy.jobs.internal.JSpyJobRegistry;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

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
            scheduler.startDelayed(startDelayedSeconds);
            Iterable<JobCapsule> jobs = jobRegistry.jobs();
            for (JobCapsule j : jobs) {
                scheduler.scheduleJob(j.job(), j.trigger());
            }
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


    private Scheduler newScheduler() {
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.setJobFactory(new JSpyJobFactory(jobRegistry));
            return scheduler;
        } catch (SchedulerException e) {
            throw new JSpyException(e);
        }
    }

}
