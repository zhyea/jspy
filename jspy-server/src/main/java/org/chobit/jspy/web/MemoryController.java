package org.chobit.jspy.web;


import org.chobit.jspy.model.EChartModel;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.MemoryService;
import org.chobit.jspy.tools.EChartKit;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;


    @PostMapping("/find-by-params")
    public List<LowerCaseKeyMap> findByParams(@RequestBody QueryParam param) {
        return memoryService.findByParams(param);
    }


    public EChartModel latestOneHour() {
        QueryParam param = new QueryParam();
        param.setType("堆内存");
        param.setStartTime(new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1)));
        param.setEndTime(new Date());

        List<LowerCaseKeyMap> m = memoryService.findByParams(param);
        return EChartKit.fill("堆内存", m, "event_time", "init", "used", "committed", "max");
    }

}
