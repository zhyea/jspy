package org.chobit.jspy.jobs;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.utils.HttpResult;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.chobit.jspy.core.info.Net.LOCAL_HOST_IP;
import static org.chobit.jspy.utils.HTTP.post;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public abstract class JobCapsule<T> implements Job {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final JSpyConfig config;

    public JobCapsule(JSpyConfig config) {
        this.config = config;
    }

    abstract String receivePath();

    abstract String name();

    abstract int intervalSeconds();

    abstract T collect();


    String group() {
        return config.getAppCode();
    }

    public final JobDetail job() {
        return newJob(this.getClass())
                .withIdentity(name(), group())
                .build();
    }


    public final Trigger trigger() {
        return newTrigger()
                .withIdentity(name(), group())
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(intervalSeconds()).repeatForever())
                .build();
    }


    @Override
    public void execute(JobExecutionContext context) {

        System.out.println(context.getJobDetail().getKey());
        System.out.println("-------------------------------");

        Headers headers =
                new Headers.Builder()
                        .add("appCode", config.getAppCode())
                        .add("ip", LOCAL_HOST_IP.value())
                        .build();

        T data = collect();
        if (null == data) {
            return;
        }
        HttpUrl url = receiveUrl();
        HttpResult result = post(url, headers, data);
        if (result.isFailed()) {
            logger.error("send message to {} failed.", url, result.getThrowable());
        }
    }


    private HttpUrl receiveUrl() {
        return new HttpUrl.Builder()
                .scheme(config.isUseSSL() ? "https" : "http")
                .host(config.getServerHost())
                .port(config.getServerPort())
                .addPathSegments(receivePath())
                .addPathSegment(config.getAppCode())
                .build();

    }

}
