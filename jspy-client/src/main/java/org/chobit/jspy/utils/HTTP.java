package org.chobit.jspy.utils;


import okhttp3.*;


public abstract class HTTP {


    private static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient();

    public static HttpResult post(String url, String json) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        HttpResult result = new HttpResult();
        try (Response response = client.newCall(request).execute()) {
            result.setContent(response.body().string());
            result.setStatus(response.code());
        } catch (Exception e) {
            result.setThrowable(e);
        }
        return result;
    }

}
