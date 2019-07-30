package org.chobit.jspy.model;

import org.chobit.jspy.core.metrics.Snapshot;
import org.chobit.jspy.core.model.GcRecord;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.jspy.core.constants.GcType.MAJOR;

public class GcOverview {

    private List<GcRecord> gcRecords = new LinkedList<>();

    private Histogram majorHistogram;

    private Histogram minorHistogram;


    public void addMajorHistogram(Snapshot snapshot) {
        majorHistogram = new Histogram(MAJOR.name(), snapshot);
    }

    public void addMinorHistogram(Snapshot snapshot) {
        minorHistogram = new Histogram(MAJOR.name(), snapshot);
    }

    public List<GcRecord> getGcRecords() {
        return gcRecords;
    }

    public Histogram getMajorHistogram() {
        return majorHistogram;
    }

    public Histogram getMinorHistogram() {
        return minorHistogram;
    }


}
