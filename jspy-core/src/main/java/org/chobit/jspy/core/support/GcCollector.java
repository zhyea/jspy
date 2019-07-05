package org.chobit.jspy.core.support;

import com.sun.management.GarbageCollectionNotificationInfo;
import com.sun.management.GcInfo;
import org.chobit.jspy.core.constants.GcType;
import org.chobit.jspy.core.exceptions.JSpyException;
import org.chobit.jspy.core.model.GcRecord;

import java.lang.management.MemoryUsage;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public final class GcCollector {


    private LinkedBlockingDeque<GcRecord> records;

    private int waitSeconds;

    /**
     * GcCollector 构造器
     *
     * @param capacity    GcCollector容量
     * @param waitSeconds 在GcCollector空间已满时，则等待指定时间至有可用空间
     */
    public GcCollector(int capacity, int waitSeconds) {
        this.waitSeconds = waitSeconds;
        this.records = new LinkedBlockingDeque<>(capacity);
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
        String cause = gcNotificationInfo.getGcCause();
        String name = gcNotificationInfo.getGcName();

        GcInfo gcInfo = gcNotificationInfo.getGcInfo();
        long gcId = gcInfo.getId();
        long startTime = gcInfo.getStartTime();
        long duration = gcInfo.getDuration();
        long usageBefore = sumOfUsage(gcInfo.getMemoryUsageBeforeGc().values());
        long usageAfter = sumOfUsage(gcInfo.getMemoryUsageAfterGc().values());

        GcRecord record = new GcRecord();
        record.setGcId(gcId);
        record.setType(typeOf(action));
        record.setAction(action);
        record.setCause(cause);
        record.setName(name);
        record.setStartTime(startTime);
        record.setDuration(duration);
        record.setUsageBefore(usageBefore);
        record.setUsageAfter(usageAfter);

        try {
            this.records.offer(record, waitSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new JSpyException("Collect GcRecord error.", e);
        }
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
