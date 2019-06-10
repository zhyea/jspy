package org.chobit.jspy.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class GCJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {

        System.out.println(context.getJobDetail().getKey());
        System.out.println("-------------------------------");
    }

}
