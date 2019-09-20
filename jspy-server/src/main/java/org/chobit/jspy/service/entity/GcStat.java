package org.chobit.jspy.service.entity;

import org.chobit.jspy.core.model.GcRecord;

public class GcStat extends AbstractStatEntity {

    private long gcId;

    private String type;

    private String action;

    private String name;

    private String cause;

    private long startTime;

    private long duration;

    private long usageBefore;

    private long usageAfter;

    private long majorGcCount;

    private long minorGcCount;


    public GcStat() {
    }


    public GcStat(String appCode, String ip, GcRecord record) {

        this.setAppCode(appCode);
        this.setIp(ip);

        this.setGcId(record.getGcId());
        this.setType(record.getType().name());
        this.setAction(record.getAction());
        this.setName(record.getName());
        this.setCause(record.getCause());

        this.setStartTime(record.getStartTime());
        this.setDuration(record.getDuration());

        this.setUsageBefore(record.getUsageBefore());
        this.setUsageAfter(record.getUsageAfter());

        this.setEventTime(record.getRecordTime());

        this.setMajorGcCount(record.getMajorGcCount());
        this.setMinorGcCount(record.getMinorGcCount());
    }

    public long getGcId() {
        return gcId;
    }

    public void setGcId(long gcId) {
        this.gcId = gcId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getUsageBefore() {
        return usageBefore;
    }

    public void setUsageBefore(long usageBefore) {
        this.usageBefore = usageBefore;
    }

    public long getUsageAfter() {
        return usageAfter;
    }

    public void setUsageAfter(long usageAfter) {
        this.usageAfter = usageAfter;
    }

    public long getMajorGcCount() {
        return majorGcCount;
    }

    public void setMajorGcCount(long majorGcCount) {
        this.majorGcCount = majorGcCount;
    }

    public long getMinorGcCount() {
        return minorGcCount;
    }

    public void setMinorGcCount(long minorGcCount) {
        this.minorGcCount = minorGcCount;
    }
}
