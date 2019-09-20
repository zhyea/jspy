package org.chobit.jspy.core.gauge;

import static org.chobit.jspy.core.gauge.CpuGaugeManager.cpuLoad;

public enum CPU {

    LOAD("负载") {
        @Override
        public Double value() {
            return cpuLoad();
        }
    },
    ;

    public final String alias;

    CPU(String alias) {
        this.alias = alias;
    }

    public Double value() {
        return Double.NaN;
    }

}
