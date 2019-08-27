package org.chobit.jspy.jobs;


import okhttp3.Headers;
import okhttp3.HttpUrl;
import org.chobit.jspy.JSpyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.chobit.jspy.core.info.Net.LOCAL_HOST_IP;

public abstract class AbstractJob<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final JSpyConfig config;

    public AbstractJob(JSpyConfig config) {
        this.config = config;
    }


    abstract String receivePath();

    abstract String name();

    abstract T collect();


    HttpUrl receiveUrl() {
        return new HttpUrl.Builder()
                .scheme(config.isUseSSL() ? "https" : "http")
                .host(config.getServerHost())
                .port(config.getServerPort())
                .addPathSegments(receivePath())
                .build();

    }


    Headers headers() {
        return new Headers.Builder()
                .add("appCode", config.getAppCode())
                .add("ip", LOCAL_HOST_IP.value())
                .build();
    }
}
