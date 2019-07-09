package org.chobit.jspy.core.gauge;

import static org.chobit.jspy.core.gauge.ClassLoadingGaugeManager.*;

public enum ClassLoading implements Gauge {


    TOTAL_LOADED("JVM已加载的类总数") {
        @Override
        public Long value() {
            return totalLoadedClassCount();
        }
    },


    CURRENT_LOADED("JVM当前加载的类总数") {
        @Override
        public Long value() {
            return currentLoadedClassCount();
        }
    },


    UNLOADED("JVM已卸载的类总数") {
        @Override
        public Long value() {
            return unloadedClassCount();
        }
    },

    ;


    public final String alias;

    ClassLoading(String alias) {
        this.alias = alias;
    }
}
