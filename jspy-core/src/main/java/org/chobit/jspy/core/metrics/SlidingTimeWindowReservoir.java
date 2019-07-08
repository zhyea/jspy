package org.chobit.jspy.core.metrics;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 滚动时间窗数据集
 */
public class SlidingTimeWindowReservoir implements Reservoir {


    // 允许存在相同时间戳的记录总数
    private static final int COLLISION_BUFFER = 256;
    // 执行trim的周期，即数据每达到TRIM_THRESHOLD的整数倍即执行trim操作
    private static final int TRIM_THRESHOLD = 256;
    // offsets the front of the time window for the purposes of clearing the buffer in trim
    private static final long CLEAR_BUFFER = TimeUnit.HOURS.toNanos(1) * COLLISION_BUFFER;

    private final ConcurrentSkipListMap<Long, Long> measurements;
    /**
     * 数据窗口大小
     */
    private final long window;
    private final AtomicLong lastTick;
    private final AtomicLong count;


    /**
     * 构造器
     *
     * @param window     时间窗大小
     * @param windowUnit 时间窗单位
     */
    public SlidingTimeWindowReservoir(long window, TimeUnit windowUnit) {
        this.measurements = new ConcurrentSkipListMap<>();
        this.window = windowUnit.toNanos(window) * COLLISION_BUFFER;
        this.lastTick = new AtomicLong(getTimeTick() * COLLISION_BUFFER);
        this.count = new AtomicLong();
    }

    @Override
    public int size() {
        trim();
        return measurements.size();
    }

    @Override
    public void update(long value) {
        if (count.incrementAndGet() % TRIM_THRESHOLD == 0) {
            trim();
        }
        measurements.put(getTick(), value);
    }

    @Override
    public Snapshot getSnapshot() {
        trim();
        return new UniformSnapshot(measurements.values());
    }


    /**
     * 获取数据标识，值为当前时间戳(纳秒) + 顺序码(小于256)
     */
    private long getTick() {
        for (; ; ) {
            final long oldTick = lastTick.get();
            final long tick = getTimeTick() * COLLISION_BUFFER;
            // ensure the tick is strictly incrementing even if there are duplicate ticks
            final long newTick = tick - oldTick > 0 ? tick : oldTick + 1;
            if (lastTick.compareAndSet(oldTick, newTick)) {
                return newTick;
            }
        }
    }


    /**
     * 清理数据窗+滞后缓存以外的数据
     */
    private void trim() {
        final long now = getTick();
        final long windowStart = now - window;
        final long windowEnd = now + CLEAR_BUFFER;
        if (windowStart < windowEnd) {
            measurements.headMap(windowStart).clear();
            measurements.tailMap(windowEnd).clear();
        } else {
            measurements.subMap(windowEnd, windowStart).clear();
        }
    }



    private long getTimeTick(){
        return System.nanoTime();
    }
}
