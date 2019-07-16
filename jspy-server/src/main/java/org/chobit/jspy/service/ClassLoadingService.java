package org.chobit.jspy.service;

import org.chobit.jspy.model.ClassLoadingGauge;
import org.chobit.jspy.service.beans.ClassLoadingStat;
import org.chobit.jspy.service.mapper.ClassLoadingStatMapper;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClassLoadingService {

    @Autowired
    private ClassLoadingStatMapper mapper;


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


}
