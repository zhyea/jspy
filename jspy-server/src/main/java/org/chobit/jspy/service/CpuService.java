package org.chobit.jspy.service;


import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.mapper.AssembleQueryMapper;
import org.chobit.jspy.service.mapper.CpuMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CpuService {

    @Autowired
    private CpuMapper mapper;
    @Autowired
    private AssembleQueryMapper aqMapper;


    /**
     * 写入CPU使用量数据
     */
    public int insert(String appCode, String ip, BigDecimal usage) {
        return mapper.insert(appCode, ip, usage, new Date());
    }


    /**
     * 查询CPU使用量数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, QueryParam param) {
        return aqMapper.findWithQueryParam("cpu_usage",
                appCode,
                param, null, "usage", "event_time");
    }
}
