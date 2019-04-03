package org.chobit.jspy.core.gauge;

import org.chobit.jspy.core.constants.GarbageCollector;
import org.chobit.jspy.core.model.GcSummary;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

import static org.chobit.jspy.core.constants.GcType.FGC;
import static org.chobit.jspy.core.constants.GcType.YGC;

public class GarbageCollectGauge implements Gauge {

    public String name() {
        return "Garbage Collector";
    }

    @Override
    public GcSummary[] value() {

        GcSummary ygc = new GcSummary(YGC);
        GcSummary fgc = new GcSummary(FGC);

        List<GarbageCollectorMXBean> mxBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : mxBeans) {

            String name = bean.getName();

            GarbageCollector collector = GarbageCollector.nameOf(name);
            if (null == collector) {
                System.out.println("find unregister collector:" + name);
                continue;
            }

            GcSummary gc;
            switch (collector.type) {
                case FGC: gc = fgc; break;
                case YGC: gc = ygc; break;
                default: continue;
            }

            gc.accumulate(bean.getCollectionCount(),  bean.getCollectionTime());
        }

        return new GcSummary[]{ygc, fgc};
    }

}
