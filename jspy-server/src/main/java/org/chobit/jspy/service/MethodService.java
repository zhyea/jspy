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

            stat.setPercent999(h.getPercent999());
            stat.setPercent98(h.getPercent98());
            stat.setPercent95(h.getPercent95());
            stat.setPercent90(h.getPercent90());
            stat.setPercent75(h.getPercent75());
            stat.setMedian(h.getMedian());

            list.add(stat);
        }

        return histograms.size() == methodMapper.batchInsert(list);
    }

}
