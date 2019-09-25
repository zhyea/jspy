package org.chobit.jspy.jobs;

public abstract class AbstractOneOffJob extends AbstractJob {


    public void execute() {
        collect();
    }


}
