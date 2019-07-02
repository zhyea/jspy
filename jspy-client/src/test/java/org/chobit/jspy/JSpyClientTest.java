package org.chobit.jspy;

import org.junit.Test;

public class JSpyClientTest {

    @Test
    public void start() {

        JSpyClientBuilder builder = JSpyClientBuilder.builder();

        JSpyClient client =
                builder.appCode("thisIsCode")
                        .serverHost("127.0.0.1")
                        .serverPort(8082)
                        .gcCollectIntervalSeconds(3)
                        .memoryCollectIntervalSeconds(1)
                        .threadCollectIntervalSeconds(1)
                        .build();


        client.start();

    }

}
