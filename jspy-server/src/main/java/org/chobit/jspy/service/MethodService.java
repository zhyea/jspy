package org.chobit.jspy.service;


import org.chobit.jspy.model.Histogram;
import org.chobit.jspy.model.MethodHistogram;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.entity.HistogramEntity;
import org.chobit.jspy.service.entity.MethodEntity;
import org.chobit.jspy.service.mapper.HistogramMapper;
import org.chobit.jspy.service.mapper.MethodMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.chobit.jspy.constants.HistogramType.METHOD;

@Service
@CacheConfig(cacheNames = "method")
public class MethodService {

    @Autowired
    private HistogramMapper histogramMapper;
    @Autowired
    private MethodMapper methodMapper;


    /**
     * 查询报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, QueryParam param) {
        return histogramMapper
                .findForChart(appCode, METHOD.id, param.getTarget(), param.getStartTime(), param.getEndTime());
    }

    /**
     * 根据ID获取方法记录
     */
    @Cacheable(key = "'get:'+#id")
    public MethodEntity get(int id) {
        System.out.println("----------------------------" + id);
        return methodMapper.get(id);
    }

    /**
     * 查找方法信息
     */
    public List<MethodEntity> findMethodNames(String appCode) {
        return methodMapper.findByAppCode(appCode);
    }


    /**
     * 写入方法统计数据
     */
    public boolean insertHistograms(String appCode, String ip, MethodHistogram mh) {
        if (mh.isEmpty()) {
            return true;
        }

        List<HistogramEntity> list = new LinkedList<>();

        for (Histogram h : mh.getHistograms()) {
            long failedCount = mh.failedCount(h.getName());
            list.add(new HistogramEntity(appCode, ip, METHOD, h, failedCount));
        }
        boolean allInsert = mh.size() == histogramMapper.batchInsert(list);

        for (HistogramEntity e : list) {
            insertOrUpdate(appCode, e.getName());
        }

        return allInsert;
    }


    /**
     * 写入或更新方法信息
     */
    private void insertOrUpdate(String appCode, String methodName) {
        MethodEntity entity = methodMapper.findByName(appCode, methodName);
        if (null == entity) {
            entity = new MethodEntity();
            entity.setAppCode(appCode);
            entity.setName(methodName);
        }

        Date date = new Date(SysTime.millis() - TimeUnit.DAYS.toMillis(1));
        Map<String, BigDecimal> counts = histogramMapper.countByTime(appCode, methodName, date);
        BigDecimal total = counts.getOrDefault("TOTAL", BigDecimal.ZERO);
        BigDecimal failed = counts.getOrDefault("FAILED", BigDecimal.ZERO);

        entity.setRecentCount(total.longValue());
        entity.setRecentFailed(failed.longValue());

        if (0 >= entity.getId()) {
            methodMapper.insert(entity);
        } else {
            methodMapper.update(entity);
        }
    }

}
