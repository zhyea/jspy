package com.zhyea.jspy.core.gauge;

import com.zhyea.jspy.core.model.Runtime;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RuntimeGauge implements Gauge {

    @Override
    public String name() {
        return "Process ID";
    }

    @Override
    public Runtime value() {
        RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
        String name = mxBean.getName();
        int processId = Integer.valueOf(name.split("@")[0]);

        Runtime runtime = new Runtime(name, processId);

        runtime.setStartTime(mxBean.getStartTime());
        runtime.setUpTime(mxBean.getUptime());
        runtime.setSpecName(mxBean.getSpecName());
        runtime.setVmName(mxBean.getVmName());
        runtime.setVmVendor(mxBean.getVmVendor());
        runtime.setVmVersion(mxBean.getVmVersion());
        runtime.setInputArguments(mxBean.getInputArguments());

        return runtime;
    }
}
