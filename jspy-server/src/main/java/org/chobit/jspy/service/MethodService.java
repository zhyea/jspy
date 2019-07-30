package org.chobit.jspy.service;


import org.chobit.jspy.service.beans.MethodHistogram;
import org.chobit.jspy.service.mapper.MethodHistogramMapper;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class MethodService {

    @Autowired
    private MethodHistogramMapper methodMapper;


    public boolean insert(String appCode, String ip, List<org.chobit.jspy.model.MethodHistogram> histograms) {

        if (histograms.isEmpty()) {
            return true;
        }

        List<MethodHistogram> list = new LinkedList<>();

        for (org.chobit.jspy.model.MethodHistogram h : histograms) {
            MethodHistogram stat = new MethodHistogram();
            stat.setAppCode(appCode);
            stat.setIp(ip);

            stat.setMethodId(h.getMethodId());

            stat.setStdDev(h.getStdDev());
            stat.setMin(h.getMin());
            stat.setMax(h.getMax());
            stat.setMean(h.getMean());

            stat.setPercentile999(h.getPercentile999());
            stat.setPercentile98(h.getPercentile98());
            stat.setPercentile95(h.getPercentile95());
            stat.setPercentile90(h.getPercentile90());
            stat.setPercentile75(h.getPercentile75());
            stat.setMedian(h.getMedian());

            stat.setEventTime(new Date(h.getEventTime() > 0 ? h.getEventTime() : SysTime.millis()));

            list.add(stat);
        }
        return histograms.size() == methodMapper.batchInsert(list);
    }

}
