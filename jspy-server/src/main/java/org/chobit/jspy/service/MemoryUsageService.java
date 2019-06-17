package org.chobit.jspy.service;

import org.chobit.jspy.constants.MemoryNames;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.core.model.MemoryOverview;
import org.chobit.jspy.core.model.MemoryPool;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.beans.MemoryUsage;
import org.chobit.jspy.service.mapper.MemoryPeakUsageMapper;
import org.chobit.jspy.service.mapper.MemoryUsageMapper;
import org.chobit.jspy.service.mapper.MetricQueryMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.MemoryType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.management.MemoryType.HEAP;
import static java.lang.management.MemoryType.NON_HEAP;

@Service
public class MemoryUsageService {

    /**
     * 内存用量浮动区间
     */
    private static final long USAGE_FLOATING_RANGE = 1024;

    @Autowired
    private MemoryUsageMapper usageMapper;
    @Autowired
    private MemoryPeakUsageMapper peakUsageMapper;
    @Autowired
    private MetricQueryMapper metricMapper;


    /**
     * 写入内存数据
     */
    private int insert(java.lang.management.MemoryUsage usage,
                       String appCode,
                       MemoryType type,
                       String name,
                       String[] managerNames,
                       String host,
                       Date eventTime,
                       boolean isPeak) {
        MemoryUsage m = new MemoryUsage();
        m.setType(type.name());
        m.setName(name);
        m.setManagerNames(Arrays.toString(managerNames));
        m.setHost(host);
        m.setAppCode(appCode);
        m.setInit(usage.getInit());
        m.setUsed(usage.getUsed());
        m.setCommitted(usage.getCommitted());
        m.setMax(usage.getMax());
        m.setEventTime(eventTime);
        if (isPeak) {
            return peakUsageMapper.insert(m);
        } else {
            return usageMapper.insert(m);
        }
    }

    /**
     * 写入内存数据
     */
    @Transactional
    @JSpyWatcher("memory-insert")
    public int insert(java.lang.management.MemoryUsage usage,
                      String appCode,
                      MemoryType type,
                      String name,
                      String[] managerNames,
                      String host,
                      Date eventTime) {
        System.out.println("-----------insertMem>>>>");
        return insert(usage, appCode, type, name, managerNames, host, eventTime, false);
    }

    /**
     * 写入内存数据
     */
    private int insert(java.lang.management.MemoryUsage usage,
                       MemoryOverview overview,
                       MemoryType type,
                       String name,
                       String[] managerNames,
                       boolean isPeak) {
        return insert(usage, overview.getAppCode(), type, name, managerNames, overview.getHost(), overview.getTime(), isPeak);
    }

    /**
     * 写入内存数据
     */
    private int insert(java.lang.management.MemoryUsage usage,
                       MemoryOverview overview,
                       MemoryType type,
                       String name,
                       String[] managerNames) {
        return insert(usage, overview.getAppCode(), type, name, managerNames, overview.getHost(), overview.getTime(), false);
    }


    /**
     * 写入内存数据
     */
    public boolean insert(MemoryOverview overview) {
        if (null != overview.getHeapUsage()) {
            insert(overview.getHeapUsage(), overview,
                    HEAP,
                    MemoryNames.nameOf(HEAP),
                    null);
        }
        if (null != overview.getNonHeapUsage()) {
            insert(overview.getNonHeapUsage(), overview,
                    NON_HEAP,
                    MemoryNames.nameOf(NON_HEAP),
                    null);
        }
        if (null != overview.getMemoryPools()) {
            for (MemoryPool pool : overview.getMemoryPools()) {
                insertMemoryPoolData(pool, overview);
            }
        }
        return true;
    }


    /**
     * 查询内存数据
     */
    public List<LowerCaseKeyMap> findByParams(QueryParam params) {
        return metricMapper.findByParams("memory_usage", params, "init", "used", "committed", "max", "event_time");
    }


    /**
     * 查询内存区域名称
     */
    public List<String> findMemoryNames(String appCode) {
        return usageMapper.findMemoryNames(appCode);
    }


    private void insertMemoryPoolData(MemoryPool pool, MemoryOverview overview) {
        insert(pool.getUsage(),
                overview,
                pool.getType(),
                pool.getName(),
                pool.getMemoryManagerNames());
        if (null != pool.getPeakUsage()) {
            insertMemoryPoolPeakData(pool, overview);
        }
    }


    private void insertMemoryPoolPeakData(MemoryPool pool, MemoryOverview overview) {
        MemoryUsage usage = peakUsageMapper.getLatestByName(overview.getAppCode(), pool.getName());
        if (null != pool.getPeakUsage()) {
            if (!isUsageClose(usage, pool.getPeakUsage())) {
                insert(pool.getPeakUsage(),
                        overview,
                        pool.getType(),
                        pool.getName(),
                        pool.getMemoryManagerNames(),
                        true);
            }
        }
    }


    /**
     * 判断最近的两次内存用量是否近似
     */
    private boolean isUsageClose(MemoryUsage usage, java.lang.management.MemoryUsage u) {
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
