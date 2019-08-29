package org.chobit.jspy.jobs;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.utils.HttpResult;

import java.util.concurrent.TimeUnit;

import static org.chobit.jspy.utils.HTTP.post;

public abstract class AbstractOneOffJob<T> extends AbstractJob<T> {


    public AbstractOneOffJob(JSpyConfig config) {
        super(config);
    }

    public void execute() {

        logger.info("Begin executing one-off job: {}", name());

        Headers headers = headers();

        T data = collect();

        if (null == data) {
            return;
        }

        HttpUrl url = receiveUrl();
        HttpResult result = post(url, headers, data);

        while (result.isFailed()) {
            try {
                TimeUnit.MINUTES.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.error("send message to {} failed.", url, result.getThrowable());
            result = post(url, headers, data);
        }
    }


}
