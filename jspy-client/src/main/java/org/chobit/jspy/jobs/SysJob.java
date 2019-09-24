package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.info.Sys;

public final class SysJob extends AbstractOneOffJob {

    public SysJob(JSpyConfig config) {
        super(config);
    }

    @Override
    String name() {
        return "sysInfo";
    }

    @Override
    void collect() {
        messagePack().setSysInfo(Sys.values());
    }
}
