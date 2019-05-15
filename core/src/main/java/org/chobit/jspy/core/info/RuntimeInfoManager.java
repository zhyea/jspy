package org.chobit.jspy.core.info;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

abstract class RuntimeInfoManager {


    private static final RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();


    static String bootClassPath() {
        return mxBean.getBootClassPath();
    }


    static String classPath() {
        return mxBean.getClassPath();
    }


    static List<String> inputArgs() {
        return mxBean.getInputArguments();
    }


    static String libPath() {
        return mxBean.getLibraryPath();
    }


    static String managementSpecVersion() {
        return mxBean.getManagementSpecVersion();
    }

    static String currentJVMAlias() {
        return mxBean.getName();
    }


    static String specName() {
        return mxBean.getSpecName();
    }


    static String specVendor() {
        return mxBean.getSpecVendor();
    }


    static String specVersion() {
        return mxBean.getSpecVersion();
    }


    static long startTime() {
        return mxBean.getStartTime();
    }


    static long uptime() {
        return mxBean.getUptime();
    }

    static String jvmName() {
        return mxBean.getVmName();
    }

    static String jvmVendor() {
        return mxBean.getVmVendor();
    }

    static String jvmVersion() {
        return mxBean.getVmVersion();
    }


    static boolean isBootClassPathSupported() {
        return mxBean.isBootClassPathSupported();
    }

}
