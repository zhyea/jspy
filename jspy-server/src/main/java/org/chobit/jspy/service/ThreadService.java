package org.chobit.jspy.service;


import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.model.ThreadGauge;
import org.chobit.jspy.service.beans.ThreadStat;
import org.chobit.jspy.service.mapper.MetricQueryMapper;
import org.chobit.jspy.service.mapper.ThreadStatMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ThreadService {


    @Autowired
    private ThreadStatMapper threadMapper;
    @Autowired
    private MetricQueryMapper metricMapper;

    @JSpyWatcher
    public int insert(String appCode, String ip, ThreadGauge gauge) {
        ThreadStat stat = new ThreadStat();
        stat.setAppCode(appCode);
        stat.setIp(ip);
        stat.setCurrent(gauge.getCurrent());
        stat.setPeak(gauge.getPeak());
        stat.setTotalStarted(gauge.getTotalStarted());
        stat.setDaemon(gauge.getDaemon());

        stat.setEventTime(new Date(gauge.getEventTime() > 0 ? gauge.getEventTime() : SysTime.millis()));

        return threadMapper.insert(stat);
    }


    /**
     * 查询内存数据
     */
    public List<LowerCaseKeyMap> findByParams(String appCode, QueryParam params) {
        return metricMapper.findByParams("thread_stat", appCode, params, null, "current", "peak", "total_started", "daemon", "event_time");
    }

}
