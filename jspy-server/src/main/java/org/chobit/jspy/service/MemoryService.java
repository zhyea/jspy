package org.chobit.jspy.service;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.chobit.jspy.constants.MemoryNames;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.core.model.MemoryInfo;
import org.chobit.jspy.core.model.MemoryPool;
import org.chobit.jspy.model.MemoryOverview;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.entity.MemoryStat;
import org.chobit.jspy.service.mapper.AssembleQueryMapper;
import org.chobit.jspy.service.mapper.MemoryStatMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryType;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.management.MemoryType.HEAP;
import static java.lang.management.MemoryType.NON_HEAP;

@Service
@CacheConfig(cacheNames = "mem")
public class MemoryService {

    /**
     * 峰值内存用量浮动区间
     */
    private static final long PEAK_FLOATING_RANGE = 1024 * 1024;

    /**
     * 缩减内存用量数据规模使用的浮动区间
     */
    private static final long SHRINK_FLOATING_RANGE = 10 * 1024 * 1024;

    /**
     * 普通内存用量浮动区间
     */
    private static final long COMMON_FLOATING_RANGE = 1024;


    @Autowired
    private MemoryStatMapper memMapper;

    @Autowired
    private AssembleQueryMapper aqMapper;


    private LoadingCache<String, List<String>> heapPoolNames = Caffeine.newBuilder()
            .maximumSize(20000)
            .expireAfterAccess(30, TimeUnit.DAYS)
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            .build(this::findHeapPoolNames);

    private LoadingCache<String, List<String>> nonHeapPoolNames = Caffeine.newBuilder()
            .maximumSize(20000)
            .expireAfterAccess(30, TimeUnit.DAYS)
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            .build(this::findNonHeapPoolNames);


    /**
     * 查询内存类型名称
     */
    public List<String> getMemTypeNames() {
        return Arrays.stream(MemoryType.values()).map(Enum::name).collect(Collectors.toList());
    }

    /**
     * 获取堆 内存池名称
     */
    public List<String> getHeapPoolNames(String appCode) {
        return heapPoolNames.get(appCode);
    }

    /**
     * 获取非堆内存池名称
     */
    public List<String> getNonHeapPoolNames(String appCode) {
        return nonHeapPoolNames.get(appCode);
    }

    /**
     * 获取堆 内存池名称
     */
    private List<String> findHeapPoolNames(String appCode) {
        return memMapper.findHeapPoolNames(appCode);
    }

    /**
     * 获取非堆内存池名称
     */
    private List<String> findNonHeapPoolNames(String appCode) {
        return memMapper.findNonHeapPoolNames(appCode);
    }


    /**
     * 写入内存数据，处理 java.lang.management.Memory
     * <p>
     * 写入前判断要写入的数据是否与最新数据接近
     */
    @JSpyWatcher
    private int insert(String appCode,
                       String ip,
                       MemoryInfo usage,
                       MemoryType type,
                       String name,
                       String[] managerNames,
                       long eventTime,
                       boolean isPeak) {

        int isPeak_ = isPeak ? 1 : 0;

        Date time = new Date(SysTime.millis() - TimeUnit.MINUTES.toMillis(6));
        MemoryStat latest = memMapper.getLatestByName(appCode, name, time, isPeak_);

        long floatingRange = isPeak ? PEAK_FLOATING_RANGE : COMMON_FLOATING_RANGE;

        if (null != latest && isUsageClose(usage, latest, floatingRange)) {
            return 0;
        }

        MemoryStat m = new MemoryStat();
        m.setType(type.name());
        m.setName(name);
        m.setManagerNames(Arrays.toString(managerNames));
        m.setIp(ip);
        m.setAppCode(appCode);
        m.setInit(usage.getInit());
        m.setUsed(usage.getUsed());
        m.setCommitted(usage.getCommitted());
        m.setMax(usage.getMax());
        m.setEventTime(new Date(eventTime));
        m.setIsPeak(isPeak_);

        return memMapper.insert(m);
    }


    /**
     * 写入内存数据，处理MemoryOverview
     */
    public boolean insert(String appCode, String ip, MemoryOverview overview) {

        long eventTime = overview.getTime();

        insertMemTypeData(appCode, ip, overview.getHeapUsage(), HEAP, eventTime, false);
        insertMemTypeData(appCode, ip, overview.getNonHeapUsage(), NON_HEAP, eventTime, false);
        insertMemTypeData(appCode, ip, overview.getPeakHeapUsage(), HEAP, eventTime, true);
        insertMemTypeData(appCode, ip, overview.getPeakNonHeapUsage(), NON_HEAP, eventTime, true);

        if (null != overview.getMemoryPools()) {
            for (MemoryPool pool : overview.getMemoryPools()) {
                // 写入内存池数据
                insertMemoryPoolData(appCode, ip, pool, eventTime);
            }
        }
        return true;
    }


    /**
     * 查询内存数据
     */
    @JSpyWatcher("获取内存报表数据Service")
    public List<LowerCaseKeyMap> findForChart(String appCode, QueryParam param) {
        List<LowerCaseKeyMap> result = aqMapper.findWithQueryParam("memory_stat",
                appCode,
                param,
                "`name`",
                "init", "used", "committed", "max", "event_time");

        if (param.getEndTime().getTime() - TimeUnit.DAYS.toMillis(1L) > param.getStartTime().getTime()) {
            return shrinkChartData(result);
        }
        return result;
    }


    /**
     * 写入内存类型数据
     */
    private void insertMemTypeData(String appCode, String ip, MemoryInfo memInfo, MemoryType type, long eventTime, boolean isPeak) {
        if (null == memInfo) {
            return;
        }
        String name = MemoryNames.nameOf(type);

        insert(appCode,
                ip,
                memInfo,
                type,
                name,
                null,
                eventTime,
                isPeak);

    }


    /**
     * 写入内存池数据
     */
    private void insertMemoryPoolData(String appCode, String ip, MemoryPool pool, long eventTime) {

        if (null != pool.getPeakUsage()) {
            insert(appCode,
                    ip,
                    pool.getPeakUsage(),
                    pool.getType(),
                    pool.getName(),
                    pool.getMemoryManagerNames(),
                    eventTime,
                    true);
        }

        insert(appCode,
                ip,
                pool.getUsage(),
                pool.getType(),
                pool.getName(),
                pool.getMemoryManagerNames(),
                eventTime,
                false);
    }


    /**
     * 收缩要提交给前端的数据的规模，以减少报表展示的压力
     */
    private List<LowerCaseKeyMap> shrinkChartData(List<LowerCaseKeyMap> src) {
        long tmpInit = 0;
        long tmpMax = 0;
        long tmpCommitted = 0;
        long tmpUsed = 0;

        List<LowerCaseKeyMap> result = new LinkedList<>();

        for (LowerCaseKeyMap m : src) {
            boolean filter = (Math.abs(m.getLong("init") - tmpInit) > SHRINK_FLOATING_RANGE);
            filter = filter || (Math.abs(m.getLong("max") - tmpMax) > SHRINK_FLOATING_RANGE);
            filter = filter || (Math.abs(m.getLong("committed") - tmpCommitted) > SHRINK_FLOATING_RANGE);
            filter = filter || (Math.abs(m.getLong("used") - tmpUsed) > SHRINK_FLOATING_RANGE);

            if (filter) {
                tmpInit = m.getLong("init");
                tmpMax = m.getLong("max");
                tmpCommitted = m.getLong("committed");
                tmpUsed = m.getLong("used");
                result.add(m);
            }
        }

        return result;
    }


    /**
     * 判断最近的两次内存用量是否近似，如差值在浮动区间内则认为是内存近似
     */
    private boolean isUsageClose(MemoryInfo target, MemoryStat latest, long floatRange) {

        if (Math.abs(latest.getInit() - target.getInit()) > floatRange) {
            return false;
        }
        if (Math.abs(latest.getMax() - target.getMax()) > floatRange) {
            return false;
        }
        if (Math.abs(latest.getCommitted() - target.getCommitted()) > floatRange) {
            return false;
        }
        if (Math.abs(latest.getUsed() - target.getUsed()) > floatRange) {
            return false;
        }
        return true;
    }


}
