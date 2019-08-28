package org.chobit.jspy.core.info;

import static org.chobit.jspy.core.info.OSInfoManager.*;

public enum OS implements Info {


    /**
     * 这里以单例模式获取操作系统信息
     */

    ARCH("Arch") {
        @Override
        public String value() {
            return arch();
        }
    },


    OS_NAME("OS Name") {
        @Override
        public String value() {
            return osName();
        }
    },

    OS_VERSION("OS Version") {
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
        public String value() {
            return availableProcessors() + "";
        }
    },


    /**
     * 说明：在某些平台上可能不可用；不可用则返回-1
     */
    LOAD_AVERAGE("近一分钟内平均负载") {
        @Override
        public String value() {
            return loadAverage() + "";
        }
    },

    ;


    public final String alias;

    OS(String alias) {
        this.alias = alias;
    }
}
