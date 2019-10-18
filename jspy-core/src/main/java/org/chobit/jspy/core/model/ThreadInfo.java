package org.chobit.jspy.core.model;

import java.lang.Thread.State;


public class ThreadInfo {

    private long id;

    private State state;

    private String name;

    private long cpuTime;

    private long userTime;

    private String lockName;

    private long lockOwnerId;

    private String lockOwnerName;

    private boolean suspended;

    private boolean inNative;

    private long waitedTime;

    private long waitedCount;

    private long blockedTime;

    private long blockedCount;

    private String stackInfo;

    public ThreadInfo() {}


    public ThreadInfo(java.lang.management.ThreadInfo info) {
        this.id = info.getThreadId();
        this.state = info.getThreadState();
        this.name = info.getThreadName();
        this.lockName = info.getLockName();
        this.lockOwnerId = info.getLockOwnerId();
        this.lockOwnerName = info.getLockOwnerName();
        this.suspended = info.isSuspended();
        this.inNative = info.isInNative();
        this.waitedCount = info.getWaitedCount();
        this.waitedTime = info.getWaitedTime();
        this.blockedCount = info.getBlockedCount();
        this.blockedTime = info.getBlockedTime();
        this.stackInfo = info.toString();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(long cpuTime) {
        this.cpuTime = cpuTime;
    }

    public long getUserTime() {
        return userTime;
    }

    public void setUserTime(long userTime) {
        this.userTime = userTime;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public long getLockOwnerId() {
        return lockOwnerId;
    }

    public void setLockOwnerId(long lockOwnerId) {
        this.lockOwnerId = lockOwnerId;
    }

    public String getLockOwnerName() {
        return lockOwnerName;
    }

    public void setLockOwnerName(String lockOwnerName) {
        this.lockOwnerName = lockOwnerName;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public boolean isInNative() {
        return inNative;
    }

    public void setInNative(boolean inNative) {
        this.inNative = inNative;
    }

    public long getWaitedTime() {
        return waitedTime;
    }

    public void setWaitedTime(long waitedTime) {
        this.waitedTime = waitedTime;
    }

    public long getWaitedCount() {
        return waitedCount;
    }

    public void setWaitedCount(long waitedCount) {
        this.waitedCount = waitedCount;
    }

    public long getBlockedTime() {
        return blockedTime;
    }

    public void setBlockedTime(long blockedTime) {
        this.blockedTime = blockedTime;
    }

    public long getBlockedCount() {
        return blockedCount;
    }

    public void setBlockedCount(long blockedCount) {
        this.blockedCount = blockedCount;
    }

    public String getStackInfo() {
        return stackInfo;
    }

    public void setStackInfo(String stackInfo) {
        this.stackInfo = stackInfo;
    }
}
