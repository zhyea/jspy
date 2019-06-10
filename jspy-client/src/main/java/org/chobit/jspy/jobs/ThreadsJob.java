package org.chobit.jspy.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ThreadsJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(context.getJobDetail().getKey());
        System.out.println("-------------------------------");
    }

}
