package org.chobit.jspy.core.info;

import static org.chobit.jspy.core.info.OSInfoManager.*;

public enum OS implements Info {


    /**
     * 这里以单例模式获取操作系统信息
     */

    ARCH("操作系统体系结构") {
        @Override
        public String value() {
            return arch();
        }
    },


    OS_NAME("操作系统名称") {
        @Override
        public String value() {
            return osName();
        }
    },

    OS_VERSION("操作系统版本号") {
        @Override
        public String value() {
            return osVersion();
        }
    },


    /**
     * 说明：在调用时可能会发生改变
     */
    AVAILABLE_PROCESSORS("JVM可用处理器数目") {
        @Override
        public Integer value() {
            return availableProcessors();
        }
    },


    /**
     * 说明：在某些平台上可能不可用；不可用则返回-1
     */
    LOAD_AVERAGE("近一分钟内负载平均值") {
        @Override
        public Double value() {
            return loadAverage();
        }
    },

    ;


    public final String alias;

    OS(String alias) {
        this.alias = alias;
    }
}
