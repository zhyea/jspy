package org.chobit.jspy.jobs;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.core.support.GcCollector;
import org.chobit.jspy.core.support.GcNotificationListener;
import org.chobit.jspy.model.GcOverview;

import javax.management.InstanceNotFoundException;
import java.util.concurrent.TimeUnit;

public final class GCJobCapsule extends JobCapsule<GcOverview> {

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
        return "/api/gc/receive";
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
    public GcOverview collect() {
        GcOverview overview = new GcOverview();
        gcCollector.drainTo(overview.getGcRecords());
        overview.addMajorHistogram(gcCollector.majorSnapshot());
        overview.addMinorHistogram(gcCollector.minorSnapshot());
        return overview;
    }
}
