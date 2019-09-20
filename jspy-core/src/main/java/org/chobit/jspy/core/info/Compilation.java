package org.chobit.jspy.core.info;

import static org.chobit.jspy.core.info.CompilationInfoManager.totalCompilationTime;
import static org.chobit.jspy.core.utils.Strings.formatLong;

/**
 * 编译信息
 */
public enum Compilation implements Info {

    NAME("JIT编译器") {
        @Override
        public String value() {
            return CompilationInfoManager.name();
        }
    },


    COMPILATION_TIME("总编译时间") {
        @Override
        public String value() {
            return formatLong(totalCompilationTime()) + " ms";
        }
    },

    ;


    public final String alias;

    Compilation(String alias) {
        this.alias = alias;
    }
}
