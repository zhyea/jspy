package org.chobit.jspy.model;

import java.util.Date;

public class ChartParam {


    private String target;

    private Date startTime;

    private Date endTime;

    private boolean usePeak = false;

    private int isPeak = 0;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isUsePeak() {
        return usePeak;
    }

    public void setUsePeak(boolean usePeak) {
        this.usePeak = usePeak;
    }

    public int getIsPeak() {
        return isPeak;
    }

    public void setIsPeak(int isPeak) {
        this.isPeak = isPeak;
    }
}
