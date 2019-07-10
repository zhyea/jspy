package org.chobit.jspy.service;


import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.model.ThreadInfo;
import org.chobit.jspy.service.beans.ThreadStat;
import org.chobit.jspy.service.mapper.ThreadStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {


    @Autowired
    private ThreadStatMapper threadMapper;


    @JSpyWatcher
    public int insert(String appCode, String ip, ThreadInfo gauge) {
        ThreadStat stat = new ThreadStat();
        stat.setAppCode(appCode);
        stat.setIp(ip);
        stat.setCurrent(gauge.getCurrent());
        stat.setPeak(gauge.getPeak());
        stat.setTotalStarted(gauge.getTotalStarted());
        stat.setDaemon(gauge.getDaemon());

        return threadMapper.insert(stat);
    }

}
