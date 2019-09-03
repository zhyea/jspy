package org.chobit.jspy.service;


import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.core.model.ThreadInfo;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.ThreadCount;
import org.chobit.jspy.model.ThreadOverview;
import org.chobit.jspy.service.entity.ThreadStat;
import org.chobit.jspy.service.mapper.ThreadStatMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class ThreadService {


    private static final String TABLE_NAME = "thread_stat";

    @Autowired
    private ThreadStatMapper threadMapper;
    @Autowired
    private AssembleQueryService aqService;

    private Map<String, List<ThreadInfo>> allThreads = new ConcurrentHashMap<>(16);


    @JSpyWatcher
    public int insert(String appCode, String ip, ThreadOverview overview) {
        ThreadCount count = overview.getThreadCount();

        this.allThreads.put(appCode, overview.getThreads());

        if (null == count) {
            return -1;
        }

        count.setEventTime(overview.getEventTime());

        if (isClose(appCode, count)) {
            return -1;
        }

        return threadMapper.insert(new ThreadStat(appCode, ip, count));
    }


    public List<ThreadInfo> allThreads(String appCode) {
        return this.allThreads.get(appCode);
    }


    /**
     * 判断与数据库中的最新纪录是否相同
     */
    private boolean isClose(String appCode, ThreadCount gauge) {
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
    public List<LowerCaseKeyMap> findForChart(String appCode, ChartParam params) {
        return aqService.findForChart(TABLE_NAME, appCode, params,
                "current", "peak", "total_started", "daemon", "event_time");
    }

}
