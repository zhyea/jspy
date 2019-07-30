package org.chobit.jspy.core.support;

import com.sun.management.GarbageCollectionNotificationInfo;
import com.sun.management.GcInfo;
import org.chobit.jspy.core.constants.GcType;
import org.chobit.jspy.core.exceptions.JSpyException;
import org.chobit.jspy.core.metrics.Histogram;
import org.chobit.jspy.core.metrics.SlidingTimeWindowReservoir;
import org.chobit.jspy.core.metrics.Snapshot;
import org.chobit.jspy.core.model.GcRecord;

import java.lang.management.MemoryUsage;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public final class GcCollector {


    private final LinkedBlockingDeque<GcRecord> records;

    private final int waitSeconds;

    private final LongAdder majorGcCount;

    private final LongAdder minorGcCount;

    private final Histogram majorHistogram;

    private final Histogram minorHistogram;


    /**
     * GcCollector 构造器
     *
     * @param capacity    GcCollector容量
     * @param waitSeconds 在GcCollector空间已满时，则等待指定时间至有可用空间
     */
    public GcCollector(int capacity, int waitSeconds) {
        this.waitSeconds = waitSeconds;
        this.records = new LinkedBlockingDeque<>(capacity);
        this.majorGcCount = new LongAdder();
        this.minorGcCount = new LongAdder();
        this.majorHistogram = new Histogram(new SlidingTimeWindowReservoir(5, TimeUnit.MINUTES));
        this.minorHistogram = new Histogram(new SlidingTimeWindowReservoir(5, TimeUnit.MINUTES));
    }


    public void drainTo(Collection<GcRecord> coll) {
        this.records.drainTo(coll);
    }


    public int size() {
        return records.size();
    }

    public GcRecord takeFirst() {
        return records.poll();
    }

    public void add(GarbageCollectionNotificationInfo gcNotificationInfo) {
        String action = gcNotificationInfo.getGcAction();

        GcInfo gcInfo = gcNotificationInfo.getGcInfo();

        long usageBefore = sumOfUsage(gcInfo.getMemoryUsageBeforeGc().values());
        long usageAfter = sumOfUsage(gcInfo.getMemoryUsageAfterGc().values());

        GcType gcType = typeOf(action);
        switch (gcType) {
            case MAJOR:
                majorGcCount.increment();
                majorHistogram.update(gcInfo.getDuration());
                break;
            case MINOR:
                minorGcCount.increment();
                minorHistogram.update(gcInfo.getDuration());
        }

        GcRecord record = new GcRecord();
        record.setGcId(gcInfo.getId());
        record.setType(gcType);
        record.setAction(action);
        record.setCause(gcNotificationInfo.getGcCause());
        record.setName(gcNotificationInfo.getGcName());
        record.setStartTime(gcInfo.getStartTime());
        record.setDuration(gcInfo.getDuration());
        record.setUsageBefore(usageBefore);
        record.setUsageAfter(usageAfter);
        record.setMajorGcCount(majorGcCount.sum());
        record.setMinorGcCount(minorGcCount.sum());

        try {
            this.records.offer(record, waitSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new JSpyException("Collect GcRecord error.", e);
        }
    }


    public Snapshot majorSnapshot() {
        return majorHistogram.getSnapshot();
    }

    public Snapshot minorSnapshot() {
        return minorHistogram.getSnapshot();
    }

    private static final String END_OF_MAJOR_GC = "end of major GC";

    private static final String END_OF_MINOR_GC = "end of minor GC";

    private GcType typeOf(String action) {
        switch (action) {
            case END_OF_MAJOR_GC:
                return GcType.MAJOR;
            case END_OF_MINOR_GC:
                return GcType.MINOR;
            default:
                return GcType.OTHER;
        }
    }

    private long sumOfUsage(Collection<MemoryUsage> usageList) {
        return usageList.stream().mapToLong(MemoryUsage::getUsed).sum();
    }

}
