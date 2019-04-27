package org.chobit.jspy.core.gauge;


import static org.chobit.jspy.core.gauge.MemoryGaugeManager.heapMemoryUsage;

public enum HeapMemoryUsage implements Gauge {


    INIT("初始内存") {
        @Override
        public Long value() {
            return heapMemoryUsage().getInit();
        }
    },

    USED("已使用内存") {
        @Override
        public Long value() {
            return heapMemoryUsage().getUsed();
        }
    },

    COMMITED("已提交内存") {
        @Override
        public Long value() {
            return heapMemoryUsage().getCommitted();
        }
    },

    MAX("最大可用内存") {
        @Override
        public Long value() {
            return heapMemoryUsage().getMax();
        }
    },

    ;

    public final String alias;

    HeapMemoryUsage(String alias) {
        this.alias = alias;
    }

}
