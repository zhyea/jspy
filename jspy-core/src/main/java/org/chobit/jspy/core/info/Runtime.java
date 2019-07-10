package org.chobit.jspy.core.info;

import static org.chobit.jspy.core.info.RuntimeInfoManager.*;
import static org.chobit.jspy.core.utils.Strings.mkString;

public enum Runtime implements Info {


    /**
     * 这里以单例模式获取JVM运行时信息
     */

    IS_BOOTCLASS_PATH_SUPPORTED("是否支持引导类路径搜索") {
        @Override
        public String value() {
            return isBootClassPathSupported() + "";
        }
    },

    BOOT_CLASS_PATH("引导类路径") {
        @Override
        public String value() {
            return bootClassPath();
        }
    },

    CLASS_PATH("类路径") {
        @Override
        public String value() {
            return classPath();
        }
    },


    EXECUTE_FILE_PATH("库路径") {
        @Override
        public String value() {
            return libPath();
        }
    },


    MANAGEMENT_SPEC_VERSION("管理接口规范版本") {
        @Override
        public String value() {
            return managementSpecVersion();
        }
    },


    SPEC_NAME("虚拟机规范名称") {
        @Override
        public String value() {
            return specName();
        }
    },


    SPEC_VENDOR("虚拟机规范供应商") {
        @Override
        public String value() {
            return specVendor();
        }
    },


    SPEC_VERSION("虚拟机规范版本") {
        @Override
        public String value() {
            return specVersion();
        }
    },


    START_TIME("进程启动时间") {
        @Override
        public String value() {
            return startTime() + "";
        }
    },


    UP_TIME("进程运行时间") {
        @Override
        public String value() {
            return uptime() + "";
        }
    },


    CURRENT_JVM_ALIAS("当前运行JVM别名") {
        @Override
        public String value() {
            return currentJVMAlias();
        }
    },


    JVM_NAME("JVM名称") {
        @Override
        public String value() {
            return jvmName();
        }
    },


    JVM_VENDOR("JVM厂商") {
        @Override
        public String value() {
            return jvmVendor();
        }
    },


    JVM_VERSION("JVM版本") {
        @Override
        public String value() {
            return jvmVersion();
        }
    },


    JVM_OPTIONS("JVM参数") {
        @Override
        public String value() {
            return mkString(inputArgs(), ";");
        }
    },

    ;


    public final String alias;

    Runtime(String alias) {
        this.alias = alias;
    }
}
