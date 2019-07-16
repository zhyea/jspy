package org.chobit.jspy.service;

import org.chobit.jspy.model.ClassLoadingGauge;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.beans.ClassLoadingStat;
import org.chobit.jspy.service.mapper.ClassLoadingStatMapper;
import org.chobit.jspy.service.mapper.MetricQueryMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClassLoadingService {

    @Autowired
    private ClassLoadingStatMapper mapper;
    @Autowired
    private MetricQueryMapper metricMapper;

    public int insert(String appCode, String ip, ClassLoadingGauge gauge) {
        ClassLoadingStat stat = new ClassLoadingStat();
        stat.setAppCode(appCode);
        stat.setIp(ip);
        stat.setTotalLoaded(gauge.getTotalLoaded());
        stat.setCurrentLoaded(gauge.getCurrentLoaded());
        stat.setUnloaded(gauge.getUnloaded());
        stat.setEventTime(new Date(gauge.getEventTime() > 0 ? gauge.getEventTime() : SysTime.millis()));
        return mapper.insert(stat);
    }



    /**
     * 查询类加载数据
     */
    public List<LowerCaseKeyMap> findByParams(String appCode, QueryParam params) {
        return metricMapper.findByParams("class_loading_stat", appCode, params, null, "total_loaded", "current_loaded", "unloaded", "event_time");
    }


}
