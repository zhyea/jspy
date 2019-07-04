package org.chobit.jspy.web;


import org.chobit.jspy.charts.ChartKit;
import org.chobit.jspy.charts.ChartModel;
import org.chobit.jspy.model.MemoryOverview;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.MemoryUsageService;
import org.chobit.jspy.service.beans.MemoryUsage;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memory")
public class MemoryUsageController {

    @Autowired
    private MemoryUsageService memoryService;


    @PostMapping("/find-by-params")
    public ChartModel findByParams(@RequestBody QueryParam param) {
        List<LowerCaseKeyMap> m = memoryService.findByParams(param);
        return ChartKit.fill(param.getName(), m, MemoryUsage.class);
    }


    @PostMapping("/receive/{appCode}")
    public boolean receive(@PathVariable("appCode") String appCode,
                           @RequestBody MemoryOverview overview) {
        Args.checkNotBlank(appCode, "appCode(应用码)不能为空");
        return memoryService.insert(appCode, overview);
    }


    @GetMapping("/mem-names/{appCode}")
    public List<String> memoryNames(@PathVariable("appCode") String appCode) {
        return memoryService.findMemoryNames(appCode);
    }


}
