package org.chobit.jspy.utils;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.chobit.jspy.utils.JSON.toJson;


public abstract class HTTP {

    private static Logger logger = LoggerFactory.getLogger(HTTP.class);

    private static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");

    private static final OkHttpClient client;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(new ConnectionPool(16, 5, TimeUnit.MINUTES));
        client = builder.build();
    }


    public static <T> HttpResult post(HttpUrl url, Headers headers, T target) {
        return post0(url, headers, toJson(target));
    }


    private static HttpResult post0(HttpUrl url, Headers headers, String json) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .headers(headers)
                .url(url)
                .post(body)
                .build();

        HttpResult result = new HttpResult();
        try (Response response = client.newCall(request).execute()) {
            result.setStatus(response.code());
            result.setContent(response.body().string());
        } catch (Exception e) {
            logger.error("Send message to {} error. msg: {}", url, json);
            result.setThrowable(e);
        }

        return result;
    }


    public static int connectionCount() {
        return client.connectionPool().connectionCount();
    }


    public static int idleConnectionCount() {
        return client.connectionPool().idleConnectionCount();
    }


    private HTTP() {
    }
}
