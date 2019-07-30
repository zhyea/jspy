package org.chobit.jspy.web;

import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.model.ThreadGauge;
import org.chobit.jspy.service.ThreadService;
import org.chobit.jspy.service.beans.ThreadStat;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thread")
public class ThreadController {

    @Autowired
    private ThreadService threadService;


    @PostMapping("/find-by-params")
    public ChartModel findByParams(@SessionAttribute("appCode") String appCode,
                                   @RequestBody QueryParam param) {
        List<LowerCaseKeyMap> m = threadService.findByParams(appCode, param);
        return ChartKit.fill(param.getTarget(), m, ThreadStat.class);
    }

    @PostMapping("/receive")
    public int receive(@RequestHeader("appCode") String appCode,
                       @RequestHeader("ip") String ip,
                       @RequestBody ThreadGauge threadGauge) {
        return threadService.insert(appCode, ip, threadGauge);
    }


}
