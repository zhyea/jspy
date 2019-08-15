package org.chobit.jspy.service;


import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.model.ThreadGauge;
import org.chobit.jspy.service.beans.ThreadStat;
import org.chobit.jspy.service.mapper.AssembleQueryMapper;
import org.chobit.jspy.service.mapper.ThreadStatMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ThreadService {


    @Autowired
    private ThreadStatMapper threadMapper;
    @Autowired
    private AssembleQueryMapper aqMapper;

    @JSpyWatcher
    public int insert(String appCode, String ip, ThreadGauge gauge) {
        if (isClose(appCode, gauge)) {
            return -1;
        }

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
     * 判断与数据库中的最新纪录是否相同
     */
    private boolean isClose(String appCode, ThreadGauge gauge) {
        Date time = new Date(SysTime.millis() - TimeUnit.MINUTES.toMillis(15));
        ThreadStat latest = threadMapper.getLatest(appCode, time);
        if (null == latest) {
            return false;
        }
        if (latest.getCurrent() != gauge.getCurrent()) {
            return false;
        }
        if (latest.getPeak() != gauge.getPeak()) {
            return false;
        }
        if (latest.getTotalStarted() != gauge.getTotalStarted()) {
            return false;
        }
        if (latest.getDaemon() != gauge.getDaemon()) {
            return false;
        }

        return true;
    }


    /**
     * 查询内存数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, QueryParam params) {
        return aqMapper.findWithQueryParam("thread_stat",
                appCode,
                params,
                null,
                "current", "peak", "total_started", "daemon", "event_time");
    }

}
