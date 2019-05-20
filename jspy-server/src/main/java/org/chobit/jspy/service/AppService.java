package org.chobit.jspy.service;


import org.chobit.jspy.tools.Base62;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AppService {


    private static final AtomicInteger SEQ = new AtomicInteger(1);
    private static final DecimalFormat FORMAT = new DecimalFormat("##");

    private synchronized String genShortUrl() {
        String s = System.currentTimeMillis() + FORMAT.format(SEQ.getAndIncrement());;
        if (99 == SEQ.get()) {
            SEQ.set(1);
        }
        long v = Long.parseLong(s);
        return Base62.encode(v);
    }


}
