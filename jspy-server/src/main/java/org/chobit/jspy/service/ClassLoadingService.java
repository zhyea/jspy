package org.chobit.jspy.service;

import org.chobit.jspy.model.ClassLoadingGauge;
import org.chobit.jspy.service.beans.ClassLoadingStat;
import org.chobit.jspy.service.mapper.ClassLoadingStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return mapper.insert(stat);
    }


}
