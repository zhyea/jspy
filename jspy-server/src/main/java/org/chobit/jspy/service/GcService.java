package org.chobit.jspy.service;

import org.chobit.jspy.core.model.GcRecord;
import org.chobit.jspy.service.beans.GcStat;
import org.chobit.jspy.service.mapper.GcStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GcService {


    @Autowired
    private GcStatMapper gcMapper;


    /**
     * 写入GC记录
     */
    public boolean insert(String appCode, String ip, List<GcRecord> gcRecords) {
        if (gcRecords.isEmpty()) {
            return true;
        }

        List<GcStat> gcStats = new ArrayList<>(gcRecords.size());
        for (GcRecord record : gcRecords) {
            GcStat stat = new GcStat();
            stat.setAppCode(appCode);
            stat.setIp(ip);

            stat.setGcId(record.getGcId());
            stat.setType(record.getType().name());
            stat.setAction(record.getAction());
            stat.setName(record.getName());
            stat.setCause(record.getCause());

            stat.setStartTime(record.getStartTime());
            stat.setDuration(record.getDuration());

            stat.setUsageBefore(record.getUsageBefore());
            stat.setUsageAfter(record.getUsageAfter());

            stat.setEventTime(record.getRecordTime());

            stat.setMajorGcCount(record.getMajorGcCount());
            stat.setMinorGcCount(record.getMinorGcCount());
        }
        return gcMapper.batchInsert(gcStats) == gcStats.size();
    }

}
