package org.chobit.jspy.utils;


import okhttp3.*;

import java.util.concurrent.TimeUnit;

import static org.chobit.jspy.Config.MAX_IDLE_CONNECTION;


public abstract class HTTP {


    private static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");

    private static final OkHttpClient client;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(new ConnectionPool(MAX_IDLE_CONNECTION, 5, TimeUnit.MINUTES));
        client = builder.build();
    }


    public static HttpResult post(String url, String json) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        HttpResult result = new HttpResult();
        try (Response response = client.newCall(request).execute()) {
            result.setStatus(response.code());
            result.setContent(response.body().string());
        } catch (Exception e) {
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

}
