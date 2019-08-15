package org.chobit.jspy.service;


import org.chobit.jspy.model.Histogram;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.beans.HistogramEntity;
import org.chobit.jspy.service.mapper.HistogramMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.jspy.constants.HistogramType.METHOD;

@Service
@CacheConfig(cacheNames = "method")
public class MethodService {

    @Autowired
    private HistogramMapper methodMapper;


    /**
     * 查询报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, QueryParam param, String methodName) {
        return methodMapper
                .findForChart(appCode, METHOD.id, methodName, param.getStartTime(), param.getEndTime());
    }

    /**
     * 查找方法名称
     */
    @Cacheable
    public List<LowerCaseKeyMap> findMethodNames(String appCode) {
        System.out.println("method names -----------------------------------------------------------");
        return methodMapper.findNamesAndCount(appCode, METHOD.id);
    }


    /**
     * 写入方法统计数据
     */
    public boolean insert(String appCode, String ip, List<Histogram> histograms) {

        if (histograms.isEmpty()) {
            return true;
        }

        List<HistogramEntity> list = new LinkedList<>();

        for (Histogram h : histograms) {
            list.add(new HistogramEntity(appCode, ip, METHOD, h));
        }
        return histograms.size() == methodMapper.batchInsert(list);
    }

}
