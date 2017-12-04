package com.zhyea.jspy.core.gauge;

import com.zhyea.jspy.core.constants.GarbageCollector;
import com.zhyea.jspy.core.model.GcSummary;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

import static com.zhyea.jspy.core.constants.GcType.FGC;
import static com.zhyea.jspy.core.constants.GcType.YGC;

public class GarbageCollectGauge implements Gauge {

    @Override
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
