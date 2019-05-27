package org.chobit.jspy.web;


import org.chobit.jspy.echarts.annotation.EChartModel;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.MemoryService;
import org.chobit.jspy.tools.EChartKit;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;


    @PostMapping("/find-by-params")
    public EChartModel findByParams(@RequestBody QueryParam param) {
        List<LowerCaseKeyMap> m = memoryService.findByParams(param);
        return EChartKit.fill(param.getType(), m, "event_time", "init", "used", "committed", "max");
    }


}
