package org.chobit.jspy;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PostConstruct;

public class JSpyClientStarter implements DisposableBean {


    private JSpyClient client;

    public JSpyClientStarter(JSpyProperties properties) {

        if (properties.isEnable()) {

            JSpyClientBuilder builder = JSpyClientBuilder.builder();

            builder.appCode(properties.getAppCode())
                    .serverHost(properties.getServerHost())
                    .serverPort(properties.getServerPort())
                    .useSSL(properties.isUseSsl())
                    .startDelayedSeconds(properties.getStartDelayedSeconds())
                    .memoryCollectIntervalSeconds(properties.getMemoryCollectIntervalSeconds())
                    .threadCollectIntervalSeconds(properties.getThreadCollectIntervalSeconds())
                    .gcCollectIntervalSeconds(properties.getGcCollectIntervalSeconds())
                    .classLoadingCollectIntervalSeconds(properties.getClassLoadingCollectIntervalSeconds())
                    .cpuUsageCollectIntervalSeconds(properties.getCpuUsageCollectIntervalSeconds())
                    .messageSendIntervalSeconds(properties.getMessageSendIntervalSeconds());

            WatcherConfig watcher = properties.getWatcher();

            if (null != watcher) {
                builder.watcherHistogramPeriodSeconds(watcher.getHistogramPeriod())
                        .expectNumOfWatchedMethods(watcher.getExpectNumMethods());
            }

            this.client = builder.build();
        } else {
            this.client = null;
        }
    }

    @PostConstruct
    public void autoStart() {
        if (null != client) {
            client.start();
        }
    }


    @Override
    public void destroy() {
        if (null != client) {
            client.shutdown(true);
        }
    }
}
