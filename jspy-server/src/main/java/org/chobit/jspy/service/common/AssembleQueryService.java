package org.chobit.jspy.service.common;

import org.chobit.jspy.constants.MetricTarget;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.model.page.PageResult;
import org.chobit.jspy.service.AppService;
import org.chobit.jspy.service.mapper.AssembleQueryMapper;
import org.chobit.jspy.service.mapper.MetricTargetMapper;
import org.chobit.jspy.spring.CustomConfig;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.Arrays;
import org.chobit.jspy.utils.SysTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.chobit.jspy.constants.Constants.DEFAULT_ID_COLUMN;
import static org.chobit.jspy.constants.Constants.DEFAULT_TIME_COLUMN;

/**
 * 管理各种组装查询能力，提供一些可重用的方法
 */
@Service
@CacheConfig(cacheNames = "assembleQuery")
public class AssembleQueryService {

    private static Logger logger = LoggerFactory.getLogger(AssembleQueryService.class);

    @Autowired
    private CustomConfig config;
    @Autowired
    private AssembleQueryMapper queryMapper;
    @Autowired
    private DataShrinkService shrinkService;
    @Autowired
    private AppService appService;
    @Autowired
    private MetricTargetMapper metricTargetMapper;


    @Cacheable(key = "'findNames:' + #appCode + '-' + #target")
    public List<String> findMetricTargetNames(String appCode, MetricTarget target) {
        return metricTargetMapper.findNames(appCode, target);
    }

    /**
     * 删除记录
     */
    public int deleteByDate(String tableName, String dateColumn) {
        long time = SysTime.millis() - TimeUnit.DAYS.toMillis(config.getDataReserveDates());
        Date date = new Date(time);
        return queryMapper.deleteByDate(tableName, dateColumn, date);
    }

    /**
     * 删除记录
     */
    public int deleteByDate(String tableName) {
        return deleteByDate(tableName, "event_time");
    }

    /**
     * 删除记录
     */
    public int deleteByIds(String tableName, Collection<Integer> ids) {
        if (ids.isEmpty()) {
            return 0;
        }
        logger.info("{} records will be deleted.", ids.size());
        System.out.println("---------->> " + ids.size() + " records will be deleted.");
        int t = queryMapper.deleteByIds(tableName, ids);
        System.out.println("---------->> " + t + " records has been deleted.");
        return t;
    }


    /**
     * 查询报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String tableName,
                                              String targetColumn,
                                              String appCode,
                                              ChartParam param,
                                              String... columns) {
        List<LowerCaseKeyMap> r = queryMapper.findForChart(tableName, appCode, param, targetColumn, columns);
        if (param.getEndTime().getTime() - param.getStartTime().getTime() > TimeUnit.HOURS.toMillis(2)) {
            return shrinkService.shrink(r);
        }
        return r;
    }


    /**
     * 查询报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String tableName,
                                              String appCode,
                                              ChartParam param,
                                              String... columns) {
        return findForChart(tableName, null, appCode, param, columns);
    }

    /**
     * 查询列表数据
     *
     * @param tableName     表名
     * @param searchColumns 要搜索的字段名
     * @param appCode       appCode
     * @param page          分页对象
     * @param resultColumns 查询结果字段
     * @return 查询结果
     */
    public PageResult<LowerCaseKeyMap> findInPage(String tableName,
                                                  List<String> searchColumns,
                                                  String appCode,
                                                  Page page,
                                                  String... resultColumns) {
        List<LowerCaseKeyMap> rows = queryMapper.findInPage(tableName, appCode, page, searchColumns, resultColumns);
        long total = queryMapper.countInPage(tableName, appCode, page, searchColumns);
        return new PageResult<>(total, rows);
    }


    /**
     * 表数据缩减处理
     *
     * @param tableName     表名
     * @param metricColumns 报表数据中的非指标字段
     */
    public void shrink(String tableName,
                       String[] metricColumns) {
        List<String> appCodes = appService.findAllAppCodes();
        for (String appCode : appCodes) {
            shrink(tableName, null, null, appCode, metricColumns, false, 0);
        }
    }


    /**
     * 表数据缩减处理
     *
     * @param tableName     表名
     * @param appCode       应用码
     * @param targetColumn  报表搜索的目标字段
     * @param targetName    搜索的目标名称
     * @param metricColumns 报表数据中的非指标字段
     * @param usePeak       是否存在峰值查询
     * @param isPeak        当前查询是否是峰值查询
     */
    public void shrink(String tableName,
                       String appCode,
                       String targetColumn,
                       String targetName,
                       String[] metricColumns,
                       boolean usePeak,
                       int isPeak) {
        String[] nonMetricColumns = new String[]{DEFAULT_TIME_COLUMN, DEFAULT_ID_COLUMN};
        String[] columns = Arrays.merge(nonMetricColumns, metricColumns);
        ChartParam param = buildChartParam(targetName, usePeak, isPeak);
        List<LowerCaseKeyMap> data = queryMapper.findForChart(tableName, appCode, param, targetColumn, columns);
        Set<Integer> ids = shrinkService.computeIdsToDel(data, nonMetricColumns);
        deleteByIds(tableName, ids);
    }


    /**
     * 构建报表查询参数，数据缩减使用
     *
     * @param targetName 报表目标名称
     * @param usePeak    是否存在峰值查询
     * @param isPeak     是否查询峰值
     * @return 报表参数对象
     */
    private ChartParam buildChartParam(String targetName, boolean usePeak, int isPeak) {
        long endTime = SysTime.millis() - TimeUnit.DAYS.toMillis(config.getShrinkStartDates());
        // 这里可以确保大部分数据会至少经过config.getShrinkStartDates()次shrink
        long startTime = endTime - TimeUnit.DAYS.toMillis(1);

        ChartParam param = new ChartParam();
        param.setStartTime(new Date(startTime));
        param.setEndTime(new Date(endTime));
        param.setTarget(targetName);
        param.setUsePeak(usePeak);
        param.setIsPeak(isPeak);
        return param;
    }


}
