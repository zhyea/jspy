package org.chobit.jspy.service;


import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.service.common.AssembleQueryService;
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
    private AssembleQueryService aqService;


    private static final String TABLE_NAME = "cpu_usage";


    /**
     * 写入CPU使用量数据
     */
    public int insert(String appCode, String ip, BigDecimal usage, long time) {
        return mapper.insert(appCode, ip, usage, new Date(time));
    }


    /**
     * 查询CPU使用量数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, ChartParam param) {
        return aqService.findForChart(TABLE_NAME, appCode, param, "usage", "event_time");
    }


    /**
     * 删除记录
     */
    public int delete() {
        return aqService.delete(TABLE_NAME);
    }
}
