package org.chobit.jspy;


public final class JSpyConfig {

    private String appCode;

    private String serverHost = "127.0.0.1";

    private int serverPort = 8190;

    private boolean useSSL = false;

    private int memoryCollectIntervalSeconds = 12;

    private int threadCollectIntervalSeconds = 12;

    private int gcCollectIntervalSeconds = 60 * 5;

    private int classLoadingCollectIntervalSeconds = 12;

    private int watcherHistogramPeriodSeconds = 60 * 5;

    private int expectNumOfWatchedMethods = 32;


    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public int getMemoryCollectIntervalSeconds() {
        return memoryCollectIntervalSeconds;
    }

    public void setMemoryCollectIntervalSeconds(int memoryCollectIntervalSeconds) {
        this.memoryCollectIntervalSeconds = memoryCollectIntervalSeconds;
    }

    public int getThreadCollectIntervalSeconds() {
        return threadCollectIntervalSeconds;
    }

    public void setThreadCollectIntervalSeconds(int threadCollectIntervalSeconds) {
        this.threadCollectIntervalSeconds = threadCollectIntervalSeconds;
    }

    public int getGcCollectIntervalSeconds() {
        return gcCollectIntervalSeconds;
    }

    public void setGcCollectIntervalSeconds(int gcCollectIntervalSeconds) {
        this.gcCollectIntervalSeconds = gcCollectIntervalSeconds;
    }


    public int getClassLoadingCollectIntervalSeconds() {
        return classLoadingCollectIntervalSeconds;
    }

    public void setClassLoadingCollectIntervalSeconds(int classLoadingCollectIntervalSeconds) {
        this.classLoadingCollectIntervalSeconds = classLoadingCollectIntervalSeconds;
    }


    public int getWatcherHistogramPeriodSeconds() {
        return watcherHistogramPeriodSeconds;
    }

    public void setWatcherHistogramPeriodSeconds(int watcherHistogramPeriodSeconds) {
        this.watcherHistogramPeriodSeconds = watcherHistogramPeriodSeconds;
    }

    public int getExpectNumOfWatchedMethods() {
        return expectNumOfWatchedMethods;
    }

    public void setExpectNumOfWatchedMethods(int expectNumOfWatchedMethods) {
        this.expectNumOfWatchedMethods = expectNumOfWatchedMethods;
    }
}
