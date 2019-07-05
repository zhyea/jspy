package org.chobit.jspy.core.model;

import org.chobit.jspy.core.constants.GcType;

public class GcRecord {

    private long gcId;

    private GcType type;

    private String action;

    private String name;

    private String cause;

    private long startTime;

    private long duration;

    private long usageBefore;

    private long usageAfter;

    private long recordTime = System.currentTimeMillis();

    public long getGcId() {
        return gcId;
    }

    public void setGcId(long gcId) {
        this.gcId = gcId;
    }

    public void setRecordTime(long recordTime) {
        this.recordTime = recordTime;
    }

    public GcType getType() {
        return type;
    }

    public void setType(GcType type) {
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

    public long getRecordTime() {
        return recordTime;
    }

    @Override
    public String toString() {
        return "GcRecord{" +
                "gcId=" + gcId +
                ", type=" + type +
                ", action='" + action + '\'' +
                ", name='" + name + '\'' +
                ", cause='" + cause + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", usageBefore=" + usageBefore +
                ", usageAfter=" + usageAfter +
                ", recordTime=" + recordTime +
                '}';
    }
}
