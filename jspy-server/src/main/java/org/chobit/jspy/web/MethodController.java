package org.chobit.jspy.web;


import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.MethodHistogram;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.MethodService;
import org.chobit.jspy.service.entity.HistogramEntity;
import org.chobit.jspy.service.entity.MethodEntity;
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
                           @RequestBody MethodHistogram histogram) {
        return methodService.insertHistograms(appCode, ip, histogram);
    }


    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @SessionAttribute("methodName") String methodName,
                                   @RequestBody QueryParam param) {
        param.setTarget(methodName);
        List<LowerCaseKeyMap> m = methodService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, HistogramEntity.class);
    }


    @GetMapping("/all-methods")
    public List<MethodEntity> allMethods(@SessionAttribute("appCode") String appCode) {
        return methodService.findMethodNames(appCode);
    }


}
