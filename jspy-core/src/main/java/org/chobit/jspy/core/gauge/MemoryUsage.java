package org.chobit.jspy.core.gauge;


import static org.chobit.jspy.core.gauge.MemoryGaugeManager.heapMemoryUsage;
import static org.chobit.jspy.core.gauge.MemoryGaugeManager.nonHeapMemoryUsage;

public enum MemoryUsage implements Gauge {

    INIT("初始内存") {
        @Override
        public Long value() {
            return heapMemoryUsage().getInit() + nonHeapMemoryUsage().getInit();
        }
    },

    USED("已使用内存") {
        @Override
        public Long value() {
            return heapMemoryUsage().getUsed() + nonHeapMemoryUsage().getUsed();
        }
    },

    COMMITED("已提交内存") {
        @Override
        public Long value() {
            return heapMemoryUsage().getCommitted() + nonHeapMemoryUsage().getCommitted();
        }
    },

    MAX("最大可用内存") {
        @Override
        public Long value() {
            return heapMemoryUsage().getMax() + nonHeapMemoryUsage().getMax();
        }
    },

    ;

    public final String alias;

    MemoryUsage(String alias) {
        this.alias = alias;
    }
}
