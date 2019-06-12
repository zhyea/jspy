package org.chobit.jspy;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jspy")
public class JSpyProperties {


    private String appCode;

    private String serverHost;

    private int serverPort;

    private int memoryCollectIntervalSeconds = 6;

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
}
