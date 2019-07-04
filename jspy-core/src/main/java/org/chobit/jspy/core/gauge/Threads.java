package org.chobit.jspy.core.gauge;


import static org.chobit.jspy.core.gauge.ThreadsGaugeManager.*;

public enum Threads implements Gauge {

    THREAD_COUNT("活跃线程数"){
        @Override
        public Long value() {
            return threadCount();
        }
    },

    PEAK_THREAD_COUNT("最大活跃线程数"){
        @Override
        public Long value() {
            return peakThreadCount();
        }
    },

    TOTAL_STARTED_THREAD_COUNT("启动过的线程总数"){
        @Override
        public Long value() {
            return totalStartedThreadCount();
        }
    },

    DAEMON_THREAD_COUNT("活跃后台线程数"){
        @Override
        public Long value() {
            return daemonThreadCount();
        }
    }

    ;

    public final String alias;


    Threads(String alias) {
        this.alias = alias;
    }
}
