package org.chobit.jspy.web;

import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.ClassLoadingGauge;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.ClassLoadingService;
import org.chobit.jspy.service.entity.ClassLoadingStat;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-load")
public class ClassLoadingController {

    @Autowired
    private ClassLoadingService classLoadingService;



    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody QueryParam param) {
        List<LowerCaseKeyMap> m = classLoadingService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, ClassLoadingStat.class);
    }

    @PostMapping("/receive")
    public int receive(@RequestHeader("appCode") String appCode,
                       @RequestHeader("ip") String ip,
                       @RequestBody ClassLoadingGauge gauge) {
        return classLoadingService.insert(appCode, ip, gauge);
    }


}
