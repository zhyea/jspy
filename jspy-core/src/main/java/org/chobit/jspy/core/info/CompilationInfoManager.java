package org.chobit.jspy.core.info;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

abstract class CompilationInfoManager {

    private static final CompilationMXBean mxBean = ManagementFactory.getCompilationMXBean();

    static String name() {
        return mxBean.getName();
    }


    static long totalCompilationTime() {
        return mxBean.getTotalCompilationTime();
    }


    private CompilationInfoManager() {
    }
}
