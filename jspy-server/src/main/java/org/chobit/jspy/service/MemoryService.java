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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private int insert(String appCode,
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
        if (null != overview.getHeapUsage()) {
            insert(appCode,
                    ip,
                    overview.getHeapUsage(),
                    HEAP,
                    MemoryNames.nameOf(HEAP),
                    null,
                    overview.getTime(),
                    false);
        }
        if (null != overview.getNonHeapUsage()) {
            insert(appCode,
                    ip,
                    overview.getNonHeapUsage(),
                    NON_HEAP,
                    MemoryNames.nameOf(NON_HEAP),
                    null,
                    overview.getTime(),
                    false);
        }
        if (null != overview.getMemoryPools()) {
            for (MemoryPool pool : overview.getMemoryPools()) {
                insertMemoryPoolData(appCode, ip, pool, overview);
            }
        }
        return true;
    }


    /**
     * 查询内存数据
     */
    public List<LowerCaseKeyMap> findByParams(String appCode, QueryParam params) {
        return metricMapper.findByParams("memory_stat", appCode, params, "`name`", "init", "used", "committed", "max", "event_time");
    }


    /**
     * 写入内存池数据
     */
    private void insertMemoryPoolData(String appCode, String ip, MemoryPool pool, MemoryOverview overview) {
        insert(appCode,
                ip,
                pool.getUsage(),
                pool.getType(),
                pool.getName(),
                pool.getMemoryManagerNames(),
                overview.getTime(),
                false);
        if (null != pool.getPeakUsage()) {
            insertMemoryPoolPeakData(appCode, ip, pool, overview);
        }
    }


    /**
     * 写入内存池峰值数据
     */
    private void insertMemoryPoolPeakData(String appCode, String ip, MemoryPool pool, MemoryOverview overview) {
        MemoryStat usage = memMapper.getLatestPeakByName(appCode, pool.getName());
        if (null != pool.getPeakUsage()) {
            if (null == usage || !isUsageClose(usage, pool.getPeakUsage())) {
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
     * 内存用量浮动区间
     */
    private static final long USAGE_FLOATING_RANGE = 1024;

    /**
     * 判断最近的两次内存用量是否近似，如差值在浮动区间内则认为是内存近似
     */
    private boolean isUsageClose(MemoryStat usage, MemoryInfo u) {
        if (Math.abs(usage.getInit() - u.getInit()) > USAGE_FLOATING_RANGE) {
            return false;
        }
        if (Math.abs(usage.getMax() - u.getMax()) > USAGE_FLOATING_RANGE) {
            return false;
        }
        if (Math.abs(usage.getCommitted() - u.getCommitted()) > USAGE_FLOATING_RANGE) {
            return false;
        }
        if (Math.abs(usage.getUsed() - u.getUsed()) > USAGE_FLOATING_RANGE) {
            return false;
        }
        return true;
    }

}
