package org.chobit.jspy;


import org.chobit.jspy.tools.PropKit;

import static org.chobit.jspy.tools.PropKit.*;


/**
 * 用来读取配置信息并构建JSpyClient实例
 */
abstract class Client {

    static {
        PropKit.load("/jspy.properties");
    }

    private Client() {
        throw new UnsupportedOperationException("private constructor, cannot be called.");
    }

    static JSpyClient build() {

        String appCode = getProp("appCode");
        String serverHost = getProp("serverHost");
        int serverPort = getInt("serverPort");
        boolean useSSL = getBoolean("useSSL");
        int startDelayedSeconds = getInt("startDelayedSeconds");
        int memoryCollectIntervalSeconds = getInt("memoryCollectIntervalSeconds");
        int threadCollectIntervalSeconds = getInt("threadCollectIntervalSeconds");
        int gcCollectIntervalSeconds = getInt("gcCollectIntervalSeconds");
        int classLoadingCollectIntervalSeconds = getInt("classLoadingCollectIntervalSeconds");
        int cpuUsageCollectIntervalSeconds = getInt("cpuUsageCollectIntervalSeconds");
        int messageSendIntervalSeconds = getInt("messageSendIntervalSeconds");
        int watcherHistogramPeriodSeconds = getInt("watcherHistogramPeriodSeconds");
        int expectNumOfWatchedMethods = getInt("expectNumOfWatchedMethods");

        JSpyClientBuilder builder = JSpyClientBuilder.builder();
        builder.appCode(appCode)
                .serverHost(serverHost)
                .serverPort(serverPort)
                .useSSL(useSSL);

        if (startDelayedSeconds > 0) {
            builder.startDelayedSeconds(startDelayedSeconds);
        }
        if (memoryCollectIntervalSeconds > 0) {
            builder.memoryCollectIntervalSeconds(memoryCollectIntervalSeconds);
        }
        if (threadCollectIntervalSeconds > 0) {
            builder.threadCollectIntervalSeconds(threadCollectIntervalSeconds);
        }
        if (gcCollectIntervalSeconds > 0) {
            builder.gcCollectIntervalSeconds(gcCollectIntervalSeconds);
        }
        if (classLoadingCollectIntervalSeconds > 0) {
            builder.classLoadingCollectIntervalSeconds(classLoadingCollectIntervalSeconds);
        }
        if (cpuUsageCollectIntervalSeconds > 0) {
            builder.cpuUsageCollectIntervalSeconds(cpuUsageCollectIntervalSeconds);
        }
        if (messageSendIntervalSeconds > 0) {
            builder.messageSendIntervalSeconds(messageSendIntervalSeconds);
        }
        if (watcherHistogramPeriodSeconds > 0) {
            builder.watcherHistogramPeriodSeconds(watcherHistogramPeriodSeconds);
        }
        if (expectNumOfWatchedMethods > 0) {
            builder.expectNumOfWatchedMethods(expectNumOfWatchedMethods);
        }

        return builder.build();
    }

}
