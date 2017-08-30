package com.zhyea.jspy.stats;

import com.zhyea.jspy.model.GarbageCollector;
import com.zhyea.jspy.model.GarbageCollectorOverview;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zhyea.jspy.constants.CollectorNames.OLD_GEN_COLLECTOR_NAMES;
import static com.zhyea.jspy.constants.CollectorNames.YOUNG_GEN_COLLECTOR_NAMES;

public class GarbageCollectorStat {

    public List<GarbageCollector> get() {
        List<GarbageCollectorMXBean> mxBeans = ManagementFactory.getGarbageCollectorMXBeans();
        List<GarbageCollector> garbageCollectors = new ArrayList<>(8);
        for (GarbageCollectorMXBean mxBean : mxBeans) {
            String name = mxBean.getName();
            boolean isValid = mxBean.isValid();
            String[] memoryPoolNames = mxBean.getMemoryPoolNames();
            long collectionCount = mxBean.getCollectionCount();
            long collectionTime = mxBean.getCollectionTime();
            garbageCollectors.add(new GarbageCollector(name, isValid, memoryPoolNames, collectionCount, collectionTime));
        }
        return garbageCollectors;
    }


    public GarbageCollectorOverview getOverview(List<GarbageCollector> gcs) {
        GarbageCollectorOverview overview = new GarbageCollectorOverview();
        for (GarbageCollector gc : gcs) {
            String name = gc.getName();
            if(Arrays.binarySearch(OLD_GEN_COLLECTOR_NAMES, name) > 0){
                overview.addAndGetOldCollectCount(gc.getCollectionCount());
                overview.addAndGetOldCollectTime(gc.getCollectionTime());
            }else if(Arrays.binarySearch(YOUNG_GEN_COLLECTOR_NAMES, name) > 0){
                overview.addAndGetYoungCollectCount(gc.getCollectionCount());
                overview.addAndGetYoungCollectTime(gc.getCollectionTime());
            }else{
                throw new RuntimeException("name:[" + name + "] hasn't been registered");
            }
        }
        return overview;
    }

}
