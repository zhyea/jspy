package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;

public abstract class AbstractOneOffJob extends AbstractJob {


    public AbstractOneOffJob(JSpyConfig config) {
        super(config);
    }

    public void execute() {
        collect();
    }


}
