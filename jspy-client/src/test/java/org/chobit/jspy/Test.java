package org.chobit.jspy;


import okhttp3.HttpUrl;

public class Test {


    public static void main(String[] args) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https" )
                .host("127.0.0.1")
                .port(8190)
                .addPathSegments("m/z")
                .addPathSegment("bbc")
                .build();

        System.out.println(url.toString());
    }


}
