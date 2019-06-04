package org.chobit.jspy.service;

import org.chobit.jspy.service.beans.Memory;
import org.chobit.jspy.service.mapper.MemoryMapper;
import org.chobit.jspy.service.mapper.MetricQueryMapper;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.List;

@Service
public class MemoryService {


    @Autowired
    private MemoryMapper memoryMapper;
    @Autowired
    private MetricQueryMapper metricMapper;

    public int insert(Memory memory) {
        return memoryMapper.insert(memory);
    }

    public int insert(MemoryUsage usage, String appId, String type) {
        Memory m = new Memory();
        m.setType(type);
        m.setAppCode(appId);
        m.setInit(usage.getInit());
        m.setUsed(usage.getUsed());
        m.setCommitted(usage.getCommitted());
        m.setMax(usage.getMax());
        m.setEventTime(new Date());
        return memoryMapper.insert(m);
    }


    public List<LowerCaseKeyMap> findByParams(QueryParam params) {
        return metricMapper.findByParams("memory", params, "init", "used", "committed", "max", "event_time");
    }


}
