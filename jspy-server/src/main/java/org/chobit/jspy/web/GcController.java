package org.chobit.jspy.web;

import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.GcOverview;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.model.chart.GcHistogram;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.model.page.PageResult;
import org.chobit.jspy.service.GcService;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gc")
public class GcController {

    @Autowired
    private GcService gcService;


    @PostMapping("/receive")
    public boolean receive(@RequestHeader("appCode") String appCode,
                           @RequestHeader("ip") String ip,
                           @RequestBody GcOverview overview) {
        return gcService.insert(appCode, ip, overview);
    }


    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody QueryParam param) {
        param.setUsePeak(true);
        List<LowerCaseKeyMap> m = gcService.findByQueryParam(appCode, param);
        return ChartKit.fill(param.getTarget(), m, GcHistogram.class);
    }


    @PostMapping("/find-in-page")
    public PageResult<LowerCaseKeyMap> findInPage(@SessionAttribute("appCode") String appCode,
                                                  @RequestBody Page page) {
        return gcService.findInPage(appCode, page);
    }


}
