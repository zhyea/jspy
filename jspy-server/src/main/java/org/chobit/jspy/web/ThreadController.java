package org.chobit.jspy.web;

import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.model.ThreadGauge;
import org.chobit.jspy.service.ThreadService;
import org.chobit.jspy.service.beans.ThreadStat;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    private ThreadService threadService;


    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody QueryParam param) {
        List<LowerCaseKeyMap> m = threadService.findByParams(appCode, param);
        return ChartKit.fill(param.getCondition(), m, ThreadStat.class);
    }

    @PostMapping("/receive")
    public int receive(@RequestHeader("appCode") String appCode,
                       @RequestHeader("ip") String ip,
                       @RequestBody ThreadGauge threadGauge) {
        Args.checkNotBlank(appCode, "appCode(应用码)不能为空");
        return threadService.insert(appCode, ip, threadGauge);
    }


}
