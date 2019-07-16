package org.chobit.jspy.web;

import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.ClassLoadingGauge;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.ClassLoadingService;
import org.chobit.jspy.service.beans.ClassLoadingStat;
import org.chobit.jspy.service.beans.MemoryStat;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-load")
public class ClassLoadingController {

    @Autowired
    private ClassLoadingService classLoadingService;



    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody QueryParam param) {
        List<LowerCaseKeyMap> m = classLoadingService.findByParams(appCode, param);
        return ChartKit.fill(param.getCondition(), m, ClassLoadingStat.class);
    }

    @PostMapping("/receive")
    public int receive(@RequestHeader("appCode") String appCode,
                       @RequestHeader("ip") String ip,
                       @RequestBody ClassLoadingGauge gauge) {
        Args.checkNotBlank(appCode, "appCode(应用码)不能为空");
        return classLoadingService.insert(appCode, ip, gauge);
    }


}
