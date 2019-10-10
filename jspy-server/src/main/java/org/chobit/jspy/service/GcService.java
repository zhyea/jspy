package org.chobit.jspy.service;

import org.chobit.jspy.constants.MetricTarget;
import org.chobit.jspy.core.model.GcRecord;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.GcOverview;
import org.chobit.jspy.model.Histogram;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.model.page.PageResult;
import org.chobit.jspy.service.common.AssembleQueryService;
import org.chobit.jspy.service.common.HistogramService;
import org.chobit.jspy.service.entity.GcStat;
import org.chobit.jspy.service.mapper.GcStatMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.chobit.jspy.constants.HistogramType.GC;

@Service
@CacheConfig(cacheNames = "gc")
public class GcService {

    @Autowired
    private GcStatMapper gcMapper;
    @Autowired
    private HistogramService histogramService;
    @Autowired
    private AssembleQueryService aqService;
    @Autowired
    private MetricTargetService metricTargetService;


    private static final String TABLE_NAME = "gc_stat";


    /**
     * 分页查询GC数据
     */
    public PageResult<LowerCaseKeyMap> findInPage(String appCode, Page page) {
        List<String> searchColumns = Arrays.asList("type", "name", "cause");
        return aqService.findInPage(TABLE_NAME, searchColumns, appCode, page,
                "type", "name", "action", "cause", "duration", "usage_before", "usage_after", "event_time");
    }

    /**
     * 查询报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, ChartParam param) {
        return histogramService.findForChart(appCode, param, GC);
    }


    /**
     * 获取GC类型名称
     */
    public List<String> findGcNames(String appCode) {
        return aqService.findMetricTargetNames(appCode, MetricTarget.GC);
    }


    /**
     * 写入GC记录
     */
    public boolean insert(String appCode, String ip, GcOverview overview) {
        insertGcHistogram(appCode, ip, overview.getMajorHistogram());
        insertGcHistogram(appCode, ip, overview.getMinorHistogram());
        insertGcRecords(appCode, ip, overview.getGcRecords());

        return true;
    }


    private boolean insertGcHistogram(String appCode, String ip, Histogram histogram) {
        if (null == histogram) {
            return false;
        }
        metricTargetService.insert(appCode, MetricTarget.GC, histogram.getName());
        return histogramService.insert(appCode, ip, histogram, GC);
    }


    private boolean insertGcRecords(String appCode, String ip, List<GcRecord> gcRecords) {
        if (null == gcRecords || gcRecords.isEmpty()) {
            return true;
        }

        List<GcStat> gcStats = new LinkedList<>();
        for (GcRecord record : gcRecords) {
            gcStats.add(new GcStat(appCode, ip, record));
        }
        return gcMapper.batchInsert(gcStats) == gcStats.size();
    }


    /**
     * 删除记录
     */
    public int delete() {
        return aqService.deleteByDate(TABLE_NAME);
    }

}
