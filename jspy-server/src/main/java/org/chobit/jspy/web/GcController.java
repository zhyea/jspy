package org.chobit.jspy.web;

import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.chart.GcHistogram;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.model.page.PageResult;
import org.chobit.jspy.service.GcService;
import org.chobit.jspy.service.entity.GcStat;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.chobit.jspy.utils.Args.checkPage;

@RestController
@RequestMapping("/api/gc")
public class GcController {

    @Autowired
    private GcService gcService;


    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody ChartParam param) {
        param.setUsePeak(true);
        List<LowerCaseKeyMap> m = gcService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, GcHistogram.class);
    }


    @PostMapping("/find-in-page")
    public PageResult<LowerCaseKeyMap> findInPage(@SessionAttribute("appCode") String appCode,
                                                  @RequestBody Page page) {
        checkPage(page, GcStat.class);
        return gcService.findInPage(appCode, page);
    }


}
