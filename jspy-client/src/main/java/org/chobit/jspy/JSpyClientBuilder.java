package org.chobit.jspy;

import org.chobit.jspy.exception.JSpyConfigException;

import static org.chobit.jspy.core.utils.Strings.isBlank;

public final class JSpyClientBuilder {

    private JSpyClientBuilder() {
    }


    private JSpyConfig config = new JSpyConfig();


    public JSpyClientBuilder appCode(String appCode) {
        this.config.setAppCode(appCode);
        return this;
    }


    public JSpyClientBuilder serverHost(String serverHost) {
        this.config.setServerHost(serverHost);
        return this;
    }


    public JSpyClientBuilder serverPort(int serverPort) {
        this.config.setServerPort(serverPort);
        return this;
    }


    public JSpyClientBuilder useSSL(boolean useSSL) {
        this.config.setUseSSL(useSSL);
        return this;
    }


    public JSpyClientBuilder memoryCollectIntervalSeconds(int memoryCollectIntervalSeconds) {
        this.config.setMemoryCollectIntervalSeconds(memoryCollectIntervalSeconds);
        return this;
    }


    public JSpyClientBuilder threadCollectIntervalSeconds(int threadCollectIntervalSeconds) {
        this.config.setThreadCollectIntervalSeconds(threadCollectIntervalSeconds);
        return this;
    }

    public JSpyClientBuilder gcCollectIntervalSeconds(int gcCollectIntervalSeconds) {
        this.config.setGcCollectIntervalSeconds(gcCollectIntervalSeconds);
        return this;
    }

    public JSpyClientBuilder classLoadingCollectIntervalSeconds(int classLoadingCollectIntervalSeconds) {
        this.config.setClassLoadingCollectIntervalSeconds(classLoadingCollectIntervalSeconds);
        return this;
    }

    public JSpyClientBuilder methodHistogramPeriodSeconds(int methodHistogramPeriodSeconds) {
        this.config.setMethodHistogramPeriodSeconds(methodHistogramPeriodSeconds);
        return this;
    }

    public static JSpyClientBuilder builder() {
        return BuilderHolder.builder;
    }

    public JSpyClient build() {
        if (null == config) {
            throw new JSpyConfigException("JSpy Config is null.");
        }
        if (isBlank(config.getAppCode())) {
            throw new JSpyConfigException("app code is blank.");
        }
        if (isBlank(config.getServerHost())) {
            throw new JSpyConfigException("JSpy server host is blank.");
        }
        if (config.getServerPort() <= 0) {
            throw new JSpyConfigException("JSpy server port should be greater than zero.");
        }
        if (config.getMemoryCollectIntervalSeconds() <= 0) {
            throw new JSpyConfigException("Memory stat collect interval seconds should be greater than zero.");
        }
        if (config.getThreadCollectIntervalSeconds() <= 0) {
            throw new JSpyConfigException("Thread stat collect interval seconds should be greater than zero.");
        }
        if (config.getGcCollectIntervalSeconds() <= 0) {
            throw new JSpyConfigException("GC stat collect interval seconds should be greater than zero.");
        }
        if (config.getClassLoadingCollectIntervalSeconds() <= 0) {
            throw new JSpyConfigException("Class loading stat collect interval seconds should be greater than zero.");
        }
        if (config.getMethodHistogramPeriodSeconds() <= 0) {
            throw new JSpyConfigException("Method histogram period seconds should be greater than zero.");
        }

        return new JSpyClient(config);
    }


    private static class BuilderHolder {
        private static final JSpyClientBuilder builder = new JSpyClientBuilder();
    }

}
