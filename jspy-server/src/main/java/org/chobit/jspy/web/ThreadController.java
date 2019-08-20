package org.chobit.jspy.web;

import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.core.model.ThreadInfo;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.model.ThreadOverview;
import org.chobit.jspy.service.ThreadService;
import org.chobit.jspy.service.entity.ThreadStat;
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
        List<LowerCaseKeyMap> m = threadService.findForChart(appCode, param);
        return ChartKit.fill(param.getTarget(), m, ThreadStat.class);
    }


    @PostMapping("/all-threads")
    public List<ThreadInfo> allThreads(@SessionAttribute("appCode") String appCode) {
        return threadService.allThreads(appCode);
    }


    @PostMapping("/receive")
    public int receive(@RequestHeader("appCode") String appCode,
                       @RequestHeader("ip") String ip,
                       @RequestBody ThreadOverview overview) {
        return threadService.insert(appCode, ip, overview);
    }

}
