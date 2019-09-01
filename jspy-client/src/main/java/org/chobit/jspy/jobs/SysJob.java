package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.info.Sys;
import org.chobit.jspy.core.model.Item;

import java.util.List;

public final class SysJob extends AbstractOneOffJob<List<Item>> {


    public SysJob(JSpyConfig config) {
        super(config);
    }

    @Override
    String receivePath() {
        return "/api/sys/receive";
    }

    @Override
    String name() {
        return "sysInfo";
    }


    @Override
    List<Item> collect() {
        return Sys.values();
    }
}
