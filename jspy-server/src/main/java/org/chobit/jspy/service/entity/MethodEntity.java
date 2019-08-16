package org.chobit.jspy.service.entity;

public class MethodEntity extends AbstractStatEntity {


    private String name;

    private long recentCount;

    private long recentFailed;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRecentCount() {
        return recentCount;
    }

    public void setRecentCount(long recentCount) {
        this.recentCount = recentCount;
    }

    public long getRecentFailed() {
        return recentFailed;
    }

    public void setRecentFailed(long recentFailed) {
        this.recentFailed = recentFailed;
    }
}
