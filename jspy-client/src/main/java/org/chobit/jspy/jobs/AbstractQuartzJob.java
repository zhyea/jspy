package org.chobit.jspy.jobs;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.utils.HttpResult;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.chobit.jspy.utils.HTTP.post;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public abstract class AbstractQuartzJob<T> extends  AbstractJob<T> implements Job {



    public AbstractQuartzJob(JSpyConfig config) {
        super(config);
    }

    abstract int intervalSeconds();


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

        Headers headers = headers();

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


}
