package org.chobit.jspy.model;

import org.chobit.jspy.core.constants.GcType;
import org.chobit.jspy.core.metrics.Snapshot;
import org.chobit.jspy.core.model.GcRecord;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.jspy.core.constants.GcType.MAJOR;

public class GcOverview {

    private List<GcRecord> gcRecords = new LinkedList<>();

    private GcHistogram majorHistogram;

    private GcHistogram minorHistogram;

    public GcOverview() {
    }

    public void addMajorHistogram(Snapshot snapshot) {
        fromSnapshot(MAJOR, snapshot);
    }

    public void addMinorHistogram(Snapshot snapshot) {
        fromSnapshot(MAJOR, snapshot);
    }

    public List<GcRecord> getGcRecords() {
        return gcRecords;
    }

    public GcHistogram getMajorHistogram() {
        return majorHistogram;
    }

    public GcHistogram getMinorHistogram() {
        return minorHistogram;
    }


    private GcHistogram fromSnapshot(GcType type, Snapshot snapshot) {
        GcHistogram histogram = new GcHistogram();

        histogram.setType(type);

        histogram.setCount(snapshot.size());
        histogram.setStdDev((long) snapshot.getStdDev());
        histogram.setMin(snapshot.getMin());
        histogram.setMax(snapshot.getMax());
        histogram.setMean((long) snapshot.getMean());
        histogram.setPercentile999((long) snapshot.get999thPercentile());
        histogram.setPercentile98((long) snapshot.get98thPercentile());
        histogram.setPercentile95((long) snapshot.get95thPercentile());
        histogram.setPercentile90((long) snapshot.get90thPercentile());
        histogram.setPercentile75((long) snapshot.get75thPercentile());
        histogram.setMedian((long) snapshot.getMedian());

        return histogram;
    }
}
