package org.chobit.jspy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {


    private static ObjectMapper mapper = new ObjectMapper();


    private static final AtomicInteger SEQ = new AtomicInteger(9);
    private static final DecimalFormat FORMAT = new DecimalFormat("00");

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder(System.currentTimeMillis() + "");
        if (SEQ.incrementAndGet() % 10 == 0) {
            SEQ.incrementAndGet();
        }
        builder.append(FORMAT.format(SEQ.get()));
        if (99 == SEQ.get()) {
            SEQ.set(1);
        }
        System.out.println(builder.toString());
        long v = Long.parseLong(builder.reverse().toString());


        System.out.println(v);

    }
}
