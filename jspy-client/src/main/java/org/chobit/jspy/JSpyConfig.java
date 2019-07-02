package org.chobit.jspy;


public class JSpyConfig {

    private String appCode;

    private String serverHost;

    private int serverPort;

    private int memoryCollectIntervalSeconds = 6;

    private int threadCollectIntervalSeconds = 6;

    private int gcCollectIntervalSeconds = 6;


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
}
