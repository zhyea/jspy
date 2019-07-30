package org.chobit.jspy.service;

import org.chobit.jspy.constants.MemoryNames;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.core.model.MemoryPool;
import org.chobit.jspy.core.model.MemoryInfo;
import org.chobit.jspy.model.MemoryOverview;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.beans.MemoryStat;
import org.chobit.jspy.service.mapper.MemoryStatMapper;
import org.chobit.jspy.service.mapper.MetricQueryMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryType;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.management.MemoryType.HEAP;
import static java.lang.management.MemoryType.NON_HEAP;

@Service
@CacheConfig(cacheNames = "mem")
public class MemoryService {

    @Autowired
    private MemoryStatMapper memMapper;
    @Autowired
    private MetricQueryMapper metricMapper;


    /**
     * 查询内存类型名称
     */
    @Cacheable(key = "'findMemTypeNames:'+#appCode")
    public List<String> findMemTypeNames(String appCode) {
        return memMapper.findMemTypeNames(appCode);
    }

    /**
     * 获取堆 内存池名称
     */
    @Cacheable(key = "'findHeapPoolNames:'+#appCode")
    public List<String> findHeapPoolNames(String appCode) {
        return memMapper.findHeapPoolNames(appCode);
    }

    /**
     * 获取非堆内存池名称
     */
    @Cacheable(key = "'findNonHeapPoolNames:'+#appCode")
    public List<String> findNonHeapPoolNames(String appCode) {
        return memMapper.findNonHeapPoolNames(appCode);
    }


    /**
     * 写入内存数据，处理 java.lang.management.Memory
     */
    @JSpyWatcher
    private synchronized int insert(String appCode,
                                    String ip,
                                    MemoryInfo usage,
                                    MemoryType type,
                                    String name,
                                    String[] managerNames,
                                    long eventTime,
                                    boolean isPeak) {
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
        m.setIsPeak(isPeak ? 1 : 0);

        return memMapper.insert(m);
    }


    /**
     * 写入内存数据，处理MemoryOverview
     */
    public boolean insert(String appCode, String ip, MemoryOverview overview) {

        insertMemTypeData(appCode, ip, overview, HEAP);
        insertMemTypeData(appCode, ip, overview, NON_HEAP);

        if (null != overview.getMemoryPools()) {
            for (MemoryPool pool : overview.getMemoryPools()) {
                // 如存在峰值数据，则优先写入
                if (null != pool.getPeakUsage()) {
                    insertMemoryPoolPeakData(appCode, ip, pool, overview);
                }
                // 写入内存池数据
                insertMemoryPoolData(appCode, ip, pool, overview);
            }
        }
        return true;
    }


    /**
     * 查询内存数据
     */
    public List<LowerCaseKeyMap> findByParams(String appCode, QueryParam param) {
        List<LowerCaseKeyMap> result = metricMapper.findWithQueryParam("memory_stat",
                appCode,
                param,
                true,
                "`name`",
                "init", "used", "committed", "max", "event_time");

        if (param.getEndTime().getTime() - TimeUnit.DAYS.toMillis(1L) > param.getStartTime().getTime()) {
            return shrink(result);
        }
        return result;
    }


    /**
     * 写入内存类型数据
     */
    private void insertMemTypeData(String appCode, String ip, MemoryOverview overview, MemoryType type) {
        MemoryInfo memInfo = null;
        switch (type) {
            case HEAP:
                memInfo = overview.getHeapUsage();
                break;
            case NON_HEAP:
                memInfo = overview.getNonHeapUsage();
                break;
            default:
                return;
        }
        if (null == memInfo) {
            return;
        }
        String name = MemoryNames.nameOf(type);
        MemoryStat usage = getLatestByName(appCode, name);
        if (null == usage || !isUsageClose(usage, memInfo, COMMON_FLOATING_RANGE)) {
            insert(appCode,
                    ip,
                    memInfo,
                    type,
                    name,
                    null,
                    overview.getTime(),
                    false);
        }

    }


    /**
     * 写入内存池数据
     */
    private void insertMemoryPoolData(String appCode, String ip, MemoryPool pool, MemoryOverview overview) {
        MemoryStat usage = getLatestByName(appCode, pool.getName());

        if (null == usage || !isUsageClose(usage, pool.getUsage(), COMMON_FLOATING_RANGE)) {
            insert(appCode,
                    ip,
                    pool.getUsage(),
                    pool.getType(),
                    pool.getName(),
                    pool.getMemoryManagerNames(),
                    overview.getTime(),
                    false);
        }
    }


    /**
     * 写入内存池峰值数据
     */
    private void insertMemoryPoolPeakData(String appCode, String ip, MemoryPool pool, MemoryOverview overview) {
        MemoryStat usage = getLatestPeakByName(appCode, pool.getName());
        if (null != pool.getPeakUsage()) {
            if (null == usage || !isUsageClose(usage, pool.getPeakUsage(), PEAK_FLOATING_RANGE)) {
                insert(appCode,
                        ip,
                        pool.getPeakUsage(),
                        pool.getType(),
                        pool.getName(),
                        pool.getMemoryManagerNames(),
                        overview.getTime(),
                        true);
            }
        }
    }


    /**
     * 收缩要提交给前端的数据的规模，以减少报表展示的压力
     */
    private List<LowerCaseKeyMap> shrink(List<LowerCaseKeyMap> src) {
        long tmpInit = 0;
        long tmpMax = 0;
        long tmpCommitted = 0;
        long tmpUsed = 0;

        List<LowerCaseKeyMap> result = new LinkedList<>();

        for (LowerCaseKeyMap m : src) {
            boolean filter = (Math.abs(m.getLong("init") - tmpInit) > PEAK_FLOATING_RANGE);
            filter = filter || (Math.abs(m.getLong("max") - tmpMax) > PEAK_FLOATING_RANGE);
            filter = filter || (Math.abs(m.getLong("committed") - tmpCommitted) > PEAK_FLOATING_RANGE);
            filter = filter || (Math.abs(m.getLong("used") - tmpUsed) > PEAK_FLOATING_RANGE);

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
     * 峰值内存用量浮动区间
     */
    private static final long PEAK_FLOATING_RANGE = 1024 * 1024;

    /**
     * 普通内存用量浮动区间
     */
    private static final long COMMON_FLOATING_RANGE = 1024;

    /**
     * 判断最近的两次内存用量是否近似，如差值在浮动区间内则认为是内存近似
     */
    private boolean isUsageClose(MemoryStat usage, MemoryInfo u, long floatRange) {
        if (Math.abs(usage.getInit() - u.getInit()) > floatRange) {
            return false;
        }
        if (Math.abs(usage.getMax() - u.getMax()) > floatRange) {
            return false;
        }
        if (Math.abs(usage.getCommitted() - u.getCommitted()) > floatRange) {
            return false;
        }
        if (Math.abs(usage.getUsed() - u.getUsed()) > floatRange) {
            return false;
        }
        return true;
    }


    /**
     * 根据内存区域名称获取最新的内存数据
     * <p>
     * 默认取6分钟内的数据
     */
    private MemoryStat getLatestByName(String appCode, String name) {
        Date time = new Date(SysTime.millis() - TimeUnit.MINUTES.toMillis(6));
        return memMapper.getLatestByName(appCode, name, time);
    }


    /**
     * 根据内存区域名称获取最新的内存峰值数据
     * <p>
     * 默认取6分钟内的数据
     */
    private MemoryStat getLatestPeakByName(String appCode, String name) {
        Date time = new Date(SysTime.millis() - TimeUnit.MINUTES.toMillis(6));
        return memMapper.getLatestPeakByName(appCode, name, time);
    }
}
