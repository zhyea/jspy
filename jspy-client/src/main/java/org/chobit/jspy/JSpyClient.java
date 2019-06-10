package org.chobit.jspy;

import org.chobit.jspy.core.exceptions.JSpyException;
import org.chobit.jspy.jobs.JobCapsule;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.chobit.jspy.utils.Reflections.subTypeOf;

public class JSpyClient {

    private Scheduler scheduler = newScheduler();


    public void start() {
        try {
            Set<JobCapsule> jobs = jobs();
            for (JobCapsule j : jobs) {
                scheduler.scheduleJob(j.job(), j.trigger());
            }
        } catch (Exception e) {
            throw new JSpyException(e);
        }
    }


    public void shutdown() {
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
            return new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException e) {
            throw new JSpyException(e);
        }
    }

    private Set<JobCapsule> jobs() throws IllegalAccessException, InstantiationException {
        Set<Class<? extends JobCapsule>> jobClasses = subTypeOf(JobCapsule.class);
        Set<JobCapsule> jobs = new HashSet<>(jobClasses.size());
        for (Class<? extends JobCapsule> clazz : jobClasses) {
            jobs.add(clazz.newInstance());
        }
        return jobs;
    }

}
