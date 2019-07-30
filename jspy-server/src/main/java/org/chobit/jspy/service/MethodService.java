package org.chobit.jspy.service;


import org.chobit.jspy.model.Histogram;
import org.chobit.jspy.service.beans.HistogramEntity;
import org.chobit.jspy.service.mapper.HistogramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.jspy.constants.HistogramType.METHOD;

@Service
public class MethodService {

    @Autowired
    private HistogramMapper methodMapper;


    public boolean insert(String appCode, String ip, List<Histogram> histograms) {

        if (histograms.isEmpty()) {
            return true;
        }

        List<HistogramEntity> list = new LinkedList<>();

        for (Histogram h : histograms) {
            list.add(new HistogramEntity(appCode, ip, METHOD, h));
        }
        return histograms.size() == methodMapper.batchInsert(list);
    }

}
