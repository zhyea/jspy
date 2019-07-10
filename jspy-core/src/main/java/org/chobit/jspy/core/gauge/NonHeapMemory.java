package org.chobit.jspy.core.gauge;

import static org.chobit.jspy.core.gauge.MemoryGaugeManager.nonHeapMemoryUsage;

public enum NonHeapMemory implements Gauge {


    INIT("初始内存") {
        @Override
        public Long value() {
            return nonHeapMemoryUsage().getInit();
        }
    },

    USED("已使用内存") {
        @Override
        public Long value() {
            return nonHeapMemoryUsage().getUsed();
        }
    },

    COMMITTED("已提交内存") {
        @Override
        public Long value() {
            return nonHeapMemoryUsage().getCommitted();
        }
    },

    MAX("最大可用内存") {
        @Override
        public Long value() {
            return nonHeapMemoryUsage().getMax();
        }
    },

    ;

    public final String alias;

    NonHeapMemory(String alias) {
        this.alias = alias;
    }


}
