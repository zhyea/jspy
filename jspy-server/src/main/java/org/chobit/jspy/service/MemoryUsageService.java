package org.chobit.jspy.service;

import org.chobit.jspy.service.beans.MemoryUsage;
import org.chobit.jspy.service.mapper.MemoryUsageMapper;
import org.chobit.jspy.service.mapper.MetricQueryMapper;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MemoryUsageService {


    @Autowired
    private MemoryUsageMapper memoryUsageMapper;
    @Autowired
    private MetricQueryMapper metricMapper;

    public int insert(MemoryUsage memory) {
        return memoryUsageMapper.insert(memory);
    }

    public int insert(java.lang.management.MemoryUsage usage, String appCode, MemoryType type, String poolName, String[] managerNames, String host) {
        MemoryUsage m = new MemoryUsage();
        m.setType(type.name());
        m.setPoolName(poolName);
        m.setManagerNames(Arrays.toString(managerNames));
        m.setHost(host);
        m.setAppCode(appCode);
        m.setInit(usage.getInit());
        m.setUsed(usage.getUsed());
        m.setCommitted(usage.getCommitted());
        m.setMax(usage.getMax());
        m.setEventTime(new Date());
        return memoryUsageMapper.insert(m);
    }


    public List<LowerCaseKeyMap> findByParams(QueryParam params) {
        return metricMapper.findByParams("memory", params, "init", "used", "committed", "max", "event_time");
    }


    public List<String> findMemoryPoolNames() {
        return memoryUsageMapper.findMemoryPoolNames();
    }


}
