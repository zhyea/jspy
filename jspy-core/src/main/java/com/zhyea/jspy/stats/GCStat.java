package com.zhyea.jspy.stats;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.zhyea.jspy.constants.CollectorNames.OLD_GEN_COLLECTOR_NAMES;
import static com.zhyea.jspy.constants.CollectorNames.YOUNG_GEN_COLLECTOR_NAMES;

public final class GCStat {

    private AtomicLong youngGCCount = new AtomicLong(0L);

    private AtomicLong youngGCTimes = new AtomicLong(0L);

    private AtomicLong oldGCCount = new AtomicLong(0L);

    private AtomicLong oldGCTimes = new AtomicLong(0L);

    private long lastCountTime = 0;

    private final long checkInterval;

    /**
     * 构造器
     * @param intervalMills 两次统计之间的最小时间间隔。
     *                 如果当前时间与上次统计的时间之差小于该值默认不重新获取GC信息，如大于该值则重新获取GC信息
     */
    public GCStat(long intervalMills) {
        this.checkInterval = intervalMills;
    }


    public final long getYoungGCCount() {
        checkAndUpdate();
        return youngGCCount.get();
    }


    public final long getFullGCCount() {
        checkAndUpdate();
        return oldGCCount.get();
    }


    public final long getYoungGCTimesInMills() {
        checkAndUpdate();
        return youngGCTimes.get();
    }

    public final long getFullGCTimesInMills() {
        checkAndUpdate();
        return oldGCTimes.get();
    }


    public final synchronized void update() {
        clean();
        List<GarbageCollectorMXBean> list = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : list) {
            String name = bean.getName();
            if (Arrays.binarySearch(OLD_GEN_COLLECTOR_NAMES, name) >= 0) {
                oldGCCount.getAndAdd(bean.getCollectionCount());
                oldGCTimes.getAndAdd(bean.getCollectionTime());
            } else if (Arrays.binarySearch(YOUNG_GEN_COLLECTOR_NAMES, name) >= 0) {
                youngGCCount.getAndAdd(bean.getCollectionCount());
                youngGCTimes.getAndAdd(bean.getCollectionTime());
            } else {
                System.out.println("Find unregister collector:" + name);
            }
        }
        lastCountTime = System.currentTimeMillis();
    }

    private final void checkAndUpdate() {
        if (System.currentTimeMillis() - lastCountTime > checkInterval) {
            update();
        }
    }

    private final void clean() {
        youngGCCount.set(0L);
        oldGCCount.set(0L);
        youngGCTimes.set(0L);
        oldGCTimes.set(0L);
    }

}
