package org.chobit.jspy.service;

import org.chobit.jspy.constants.MetricTarget;
import org.chobit.jspy.service.common.AssembleQueryService;
import org.chobit.jspy.service.entity.MetricTargetName;
import org.chobit.jspy.service.mapper.MetricTargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricTargetService {

    @Autowired
    private MetricTargetMapper mapper;
    @Autowired
    private AssembleQueryService aqService;

    public void insert(String appCode, MetricTarget target, String name) {
        List<String> existsNames = aqService.findMetricTargetNames(appCode, target);
        if (null != existsNames && existsNames.contains(name)) {
            return;
        }
        MetricTargetName mtn = new MetricTargetName();
        mtn.setAppCode(appCode);
        mtn.setTarget(target);
        mtn.setName(name);
        mapper.insert(mtn);
    }


    public int delete() {
        return aqService.deleteByDate("metric_target_name", "op_time");
    }

}
