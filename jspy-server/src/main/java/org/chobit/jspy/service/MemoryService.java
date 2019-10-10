package org.chobit.jspy.service;

import org.chobit.jspy.constants.MemoryNames;
import org.chobit.jspy.constants.MetricTarget;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.core.model.MemoryInfo;
import org.chobit.jspy.core.model.MemoryPool;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.MemoryOverview;
import org.chobit.jspy.service.common.AssembleQueryService;
import org.chobit.jspy.service.entity.MemoryStat;
import org.chobit.jspy.service.mapper.MemoryStatMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.management.MemoryType.HEAP;
import static java.lang.management.MemoryType.NON_HEAP;
import static org.chobit.jspy.constants.MetricTarget.MEM_HEAP;
import static org.chobit.jspy.constants.MetricTarget.MEM_NON_HEAP;

@Service
public class MemoryService {


    private static final String TABLE_NAME = "memory_stat";

    @Autowired
    private MemoryStatMapper memMapper;
    @Autowired
    private AssembleQueryService aqService;
    @Autowired
    private MetricTargetService metricTargetService;
    @Autowired
    private AppService appService;


    /**
     * 查询内存类型名称
     */
    public List<String> getMemTypeNames() {
        return Arrays.stream(MemoryType.values()).map(MemoryNames::nameOf).collect(Collectors.toList());
    }

    /**
     * 获取堆 内存池名称
     */
    public List<String> getHeapPoolNames(String appCode) {
        return aqService.findMetricTargetNames(appCode, MEM_HEAP);
    }

    /**
     * 获取非堆内存池名称
     */
    public List<String> getNonHeapPoolNames(String appCode) {
        return aqService.findMetricTargetNames(appCode, MEM_NON_HEAP);
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

        MemoryStat m = new MemoryStat(appCode, ip, type, usage, name, managerNames, eventTime, isPeak);

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
    public List<LowerCaseKeyMap> findForChart(String appCode, ChartParam param) {
        return aqService.findForChart(TABLE_NAME, "`name`", appCode, param,
                "init", "used", "committed", "max", "event_time");
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

        MetricTarget target = pool.getType() == HEAP ? MEM_HEAP : MEM_NON_HEAP;

        metricTargetService.insert(appCode, target, pool.getName());
    }


    /**
     * 删除记录
     */
    public int delete() {
        return aqService.deleteByDate(TABLE_NAME);
    }


    /**
     * 执行数据缩减
     */
    public void shrink() {
        List<String> appCodes = appService.findAllAppCodes();
        for (String code : appCodes) {
            shrink(code);
        }
    }


    /**
     * 执行每个应用的数据缩减处理
     *
     * @param appCode 应用码
     */
    private void shrink(String appCode) {
        List<String> targetNames = new LinkedList<>();
        targetNames.addAll(getMemTypeNames());
        targetNames.addAll(getHeapPoolNames(appCode));
        targetNames.addAll(getNonHeapPoolNames(appCode));

        String[] metricColumns = new String[]{"init", "used", "committed", "max"};

        for (String name : targetNames) {
            aqService.shrink(TABLE_NAME, appCode, "`name`", name, metricColumns, true, 0);
            aqService.shrink(TABLE_NAME, appCode, "`name`", name, metricColumns, true, 1);
        }
    }

}
