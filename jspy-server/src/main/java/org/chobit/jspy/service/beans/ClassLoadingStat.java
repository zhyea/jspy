package org.chobit.jspy.service.beans;

public class ClassLoadingStat extends AbstractStatEntity {

    private long totalLoaded;

    private long currentLoaded;

    private long unloaded;

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
}