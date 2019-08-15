package org.chobit.jspy.web;


import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.Histogram;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.MethodService;
import org.chobit.jspy.service.beans.HistogramEntity;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/method")
public class MethodController {


    @Autowired
    private MethodService methodService;


    @PostMapping("/receive")
    public boolean receive(@RequestHeader("appCode") String appCode,
                           @RequestHeader("ip") String ip,
                           @RequestBody List<Histogram> histograms) {
        return methodService.insert(appCode, ip, histograms);
    }


    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @SessionAttribute("methodName") String methodName,
                                   @RequestBody QueryParam param) {
        List<LowerCaseKeyMap> m = methodService.findForChart(appCode, param, methodName);
        return ChartKit.fill(param.getTarget(), m, HistogramEntity.class);
    }


    @GetMapping("/all-methods")
    public List<LowerCaseKeyMap> allMethods(@SessionAttribute("appCode") String appCode) {
        return methodService.findMethodNames(appCode);
    }


}
