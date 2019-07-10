package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.model.GcRecord;
import org.chobit.jspy.core.support.GcCollector;
import org.chobit.jspy.core.support.GcNotificationListener;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class GCJobCapsule extends JobCapsule<List<GcRecord>> {

    private final GcCollector gcCollector;

    public GCJobCapsule(JSpyConfig config) {
        super(config);
        int waitSecs = (int) TimeUnit.MINUTES.toSeconds(5);
        gcCollector = new GcCollector(waitSecs * 10, waitSecs);
        try {
            new GcNotificationListener(gcCollector).apply();
        } catch (InstanceNotFoundException e) {
            logger.error("Applying GcNotificationListener error.", e);
        }
    }

    @Override
    String receivePath() {
        return "/gc/receive";
    }


    @Override
    public String name() {
        return "gc";
    }

    @Override
    int intervalSeconds() {
        return config.getGcCollectIntervalSeconds();
    }


    @Override
    public List<GcRecord> collect() {
        List<GcRecord> list = new ArrayList<>(gcCollector.size() + 6);
        gcCollector.drainTo(list);
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }
}
