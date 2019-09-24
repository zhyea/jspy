package org.chobit.jspy.model;

import org.chobit.jspy.utils.SysTime;

public class ClassLoadingCount {

    private long totalLoaded;

    private long currentLoaded;

    private long unloaded;

    private long eventTime = SysTime.millis();


    public long getTotalLoaded() {
        return totalLoaded;
    }

    public void setTotalLoaded(long totalLoaded) {
        this.totalLoaded = totalLoaded;
    }

    public long getCurrentLoaded() {
        return currentLoaded;
    }

    public void setCurrentLoaded(long currentLoaded) {
        this.currentLoaded = currentLoaded;
    }

    public long getUnloaded() {
        return unloaded;
    }

    public void setUnloaded(long unloaded) {
        this.unloaded = unloaded;
    }

    public long getEventTime() {
        return eventTime;
    }

}
