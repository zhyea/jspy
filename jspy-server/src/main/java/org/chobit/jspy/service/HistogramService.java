package org.chobit.jspy.service;


import org.chobit.jspy.constants.HistogramType;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.Histogram;
import org.chobit.jspy.service.entity.HistogramEntity;
import org.chobit.jspy.service.mapper.HistogramMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HistogramService {

    public static final String TABLE_NAME = "histogram";

    @Autowired
    private HistogramMapper histogramMapper;
    @Autowired
    private AssembleQueryService aqService;


    /**
     * 获取报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, ChartParam param, HistogramType type) {
        return histogramMapper
                .findForChart(appCode, type.id, param.getTarget(), param.getStartTime(), param.getEndTime());
    }


    /**
     * 获取报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, ChartParam param, String target, HistogramType type) {
        return histogramMapper
                .findForChart(appCode, type.id, target, param.getStartTime(), param.getEndTime());
    }


    /**
     * 获取GC类型名称
     */
    public List<String> findNames(String appCode, HistogramType type) {
        return histogramMapper.findNames(appCode, type.id);
    }


    /**
     * 写入Histogram数据
     */
    public boolean insert(String appCode, String ip, Histogram histogram, HistogramType type) {
        if (null == histogram) {
            return true;
        }
        return histogramMapper.insert(new HistogramEntity(appCode, ip, type, histogram)) > 0;
    }


    /**
     * 批量写入Histogram数据
     */
    public boolean batchInsert(List<HistogramEntity> entities) {
        int count = histogramMapper.batchInsert(entities);
        return count == entities.size();
    }


    /**
     * 按时间对目标数据进行统计
     */
    public Map<String, BigDecimal> sumByTime(String appCode, String name, Date date) {
        return histogramMapper.sumByTime(appCode, name, date);
    }


    /**
     * 删除早期数据
     */
    public int delete() {
        return aqService.delete(TABLE_NAME);
    }

}
