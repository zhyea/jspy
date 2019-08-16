package org.chobit.jspy.web;


import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.MemoryOverview;
import org.chobit.jspy.model.QueryParam;
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

    
    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody QueryParam param) {
        param.setUsePeak(true);
        List<LowerCaseKeyMap> m = memoryService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, MemoryStat.class);
    }

    @PostMapping("/find-peak-by-params")
    public ChartModel findPeakByParams(@SessionAttribute("appCode") String appCode,
                                       @RequestBody QueryParam param) {
        param.setUsePeak(true);
        param.setIsPeak(1);
        List<LowerCaseKeyMap> m = memoryService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, MemoryStat.class);
    }


    @PostMapping("/receive")
    public boolean receive(@RequestHeader("appCode") String appCode,
                           @RequestHeader("ip") String ip,
                           @RequestBody MemoryOverview overview) {
        return memoryService.insert(appCode, ip, overview);
    }

}
