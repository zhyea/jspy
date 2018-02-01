package org.chobit.jspy.core.gauge;

import org.chobit.jspy.core.model.OperatingSystem;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class OSGauge implements Gauge {
    
    @Override
    public String name() {
        return "OS";
    }

    @Override
    public OperatingSystem value() {
        OperatingSystemMXBean mxBean = ManagementFactory.getOperatingSystemMXBean();

        String name = mxBean.getName();
        String version = mxBean.getVersion();

        OperatingSystem os = new OperatingSystem(name, version);

        os.setArch(mxBean.getArch());
        os.setAvailableProcessors(mxBean.getAvailableProcessors());
        os.setSystemLoadAverage(mxBean.getSystemLoadAverage());

        return os;
    }
}
