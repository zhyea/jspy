package org.chobit.jspy.web;


import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.service.MemoryService;
import org.chobit.jspy.service.entity.MemoryStat;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @JSpyWatcher("获取内存报表数据Controller")
    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody ChartParam param) {
        param.setUsePeak(true);
        List<LowerCaseKeyMap> m = memoryService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, MemoryStat.class);
    }


    @PostMapping("/find-peak-by-params")
    public ChartModel findPeakByParams(@SessionAttribute("appCode") String appCode,
                                       @RequestBody ChartParam param) {
        param.setUsePeak(true);
        param.setIsPeak(1);
        List<LowerCaseKeyMap> m = memoryService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, MemoryStat.class);
    }

}
