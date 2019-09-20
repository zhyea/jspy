package org.chobit.jspy.model;

import org.chobit.jspy.core.model.ThreadInfo;

import java.util.List;

public class ThreadOverview {

    private long eventTime;

    private ThreadCount threadCount;

    private List<ThreadInfo> threads;

    public ThreadOverview() {
    }

    public ThreadOverview(long eventTime,
                          ThreadCount threadCount,
                          List<ThreadInfo> threads) {
        this.eventTime = eventTime;
        this.threadCount = threadCount;
        this.threads = threads;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public ThreadCount getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(ThreadCount threadCount) {
        this.threadCount = threadCount;
    }

    public List<ThreadInfo> getThreads() {
        return threads;
    }

    public void setThreads(List<ThreadInfo> threads) {
        this.threads = threads;
    }
}
