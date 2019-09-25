package org.chobit.jspy.jobs;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.model.MessagePack;
import org.chobit.jspy.utils.HttpResult;

import static org.chobit.jspy.core.info.Net.LOCAL_HOST_IP;
import static org.chobit.jspy.utils.HTTP.post;
import static org.chobit.jspy.utils.SysTime.sleepInSeconds;

public final class MessageSendJob extends AbstractQuartzJob {

    private static final String RECEIVE_PATH = "/api/message/receive";


    public MessageSendJob(JSpyConfig config) {
        super(config);
    }

    @Override
    int intervalSeconds() {
        return config.getMessageSendIntervalSeconds();
    }


    @Override
    String name() {
        return "messageSend";
    }

    @Override
    void collect() {
        MessagePack data = dumpMessages();

        HttpUrl url = receiveUrl();
        HttpResult result = post(url, headers(), data);

        int count = 0;

        while (result.isFailed() && ++count < 5) {
            sleepInSeconds(10);
            logger.error("send message to {} failed.", url, result.getThrowable());
            result = post(url, headers(), data);
        }
    }


    private HttpUrl receiveUrl() {
        return new HttpUrl.Builder()
                .scheme(config.isUseSSL() ? "https" : "http")
                .host(config.getServerHost())
                .port(config.getServerPort())
                .addPathSegments(RECEIVE_PATH)
                .build();

    }


    private Headers headers() {
        return new Headers.Builder()
                .add("appCode", config.getAppCode())
                .add("ip", LOCAL_HOST_IP.value())
                .build();
    }
}
