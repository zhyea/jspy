package org.chobit.jspy.agent.constant;

import java.util.Arrays;

/**
 * 配置信息维护类
 */
public final class Config {

    /**
     * 要监控的包。多个包名以分号分割，支持正则表达式
     */
    public static final String[] MONITOR_PACKAGES;

    static {
        PropKit.load("/jspy.properties");
        MONITOR_PACKAGES = getMonitorPackages();
    }


    private static String[] getMonitorPackages() {
        String packages = PropKit.getProp("monitor.packages");
        if (null != packages) {
            String[] arr = packages.split(";");
            Arrays.sort(arr);
            return arr;
        }
        return new String[]{};
    }


    /**
     * 是否监控所有的方法，即是否需要给所有的方法添加计时代码
     */
    public static final boolean MONITOR_ALL_METHODS =
            PropKit.getBoolean("monitor.all.methods", false);

}
