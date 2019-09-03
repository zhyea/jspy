package org.chobit.jspy.web;

import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.service.CpuService;
import org.chobit.jspy.service.entity.CpuUsage;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cpu")
public class CpuController {

    @Autowired
    private CpuService cpuService;


    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody ChartParam param) {
        List<LowerCaseKeyMap> m = cpuService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, CpuUsage.class);
    }


    @PostMapping("/receive")
    public int receive(@RequestHeader("appCode") String appCode,
                       @RequestHeader("ip") String ip,
                       @RequestBody Double usage) {
        return cpuService.insert(appCode, ip, new BigDecimal(usage));
    }


}
