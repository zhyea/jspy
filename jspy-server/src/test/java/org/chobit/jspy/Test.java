package org.chobit.jspy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(java.net.InetAddress.getLocalHost().getHostAddress());
    }

}
