package org.chobit.jspy.core.gauge;

import static org.chobit.jspy.core.gauge.ClassLoadingGaugeManager.*;

public enum ClassLoading implements Gauge {


    TOTAL_LOADED("JVM已经加载的类的数量") {
        @Override
        public Long value() {
            return totalLoadedClassCount();
        }
    },


    CURRENT_LOADED("JVM当前加载的类的数量") {
        @Override
        public Long value() {
            return currentLoadedClassCount();
        }
    },



    UNLOAD("JVM已经卸载的类的数量") {
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
