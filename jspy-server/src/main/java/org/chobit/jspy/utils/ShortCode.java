package org.chobit.jspy.utils;


import org.chobit.jspy.tools.Base62;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ShortCode {


    private static final AtomicInteger SEQ = new AtomicInteger(1);
    private static final DecimalFormat FORMAT = new DecimalFormat("00");

    /**
     * 基于时间生成八位短码
     * <p>
     * 该方法并不安全，如果调用间隔在十分之一毫秒内会出现重复值
     */
    public static synchronized String gen() {
        StringBuilder builder = new StringBuilder(System.currentTimeMillis() / 10 + "");
        if (SEQ.incrementAndGet() % 10 == 0) {
            SEQ.incrementAndGet();
        }
        builder.append(FORMAT.format(SEQ.get()));
        if (99 == SEQ.get()) {
            SEQ.set(1);
        }
        long v = Long.parseLong(builder.reverse().toString());
        return Base62.encode(v);
    }


    private ShortCode() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }
}
