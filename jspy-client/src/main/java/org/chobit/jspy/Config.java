package org.chobit.jspy;


import org.chobit.jspy.utils.PropKit;

import static org.chobit.jspy.utils.PropKit.getInt;

public abstract class Config {

    static {
        PropKit.load("/config.properties");
    }


    public static final int MAX_IDLE_CONNECTION = getInt("max.idle.connection", 16);

    public static final int MEMORY_COLLECT_INTERVAL_SECONDS
            = getInt("memory.collect.interval.seconds", 3);


}
