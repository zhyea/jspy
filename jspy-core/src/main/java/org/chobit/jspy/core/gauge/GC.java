package org.chobit.jspy.core.gauge;

public enum GC implements Gauge {


    COUNT("GC次数") {
        @Override
        public Long value() {
            return GCGaugeManager.count();
        }
    },

    USED_TIME("GC用时") {
        @Override
        public Long value() {
            return GCGaugeManager.usedTime();
        }
    },

    ;


    public final String alias;

    GC(String alias) {
        this.alias = alias;
    }

}
