package org.chobit.jspy.service;


import org.chobit.jspy.model.MethodHistogram;
import org.chobit.jspy.service.beans.MethodStat;
import org.chobit.jspy.service.mapper.MethodStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MethodService {

    @Autowired
    private MethodStatMapper methodMapper;


    public boolean insert(String appCode, String ip, List<MethodHistogram> histograms) {

        if (histograms.isEmpty()) {
            return true;
        }

        List<MethodStat> list = new LinkedList<>();

        for (MethodHistogram h : histograms) {
            MethodStat stat = new MethodStat();
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

            list.add(stat);
        }
        return histograms.size() == methodMapper.batchInsert(list);
    }

}
