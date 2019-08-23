package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.info.Sys;
import org.chobit.jspy.core.model.Item;

import java.util.List;
import java.util.concurrent.TimeUnit;

public final class SysJobCapsule extends JobCapsule<List<Item>> {


    public SysJobCapsule(JSpyConfig config) {
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
    int intervalSeconds() {
        return (int) TimeUnit.DAYS.toSeconds(60);
    }

    @Override
    List<Item> collect() {
        return Sys.values();
    }
}
