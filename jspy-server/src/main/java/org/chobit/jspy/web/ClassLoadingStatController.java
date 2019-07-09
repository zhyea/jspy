package org.chobit.jspy.web;

import org.chobit.jspy.model.ClassLoadingGauge;
import org.chobit.jspy.service.ClassLoadingService;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class-load")
public class ClassLoadingStatController {

    @Autowired
    private ClassLoadingService classLoadingService;


    @PostMapping("/receive")
    public int receive(@RequestHeader("appCode") String appCode,
                       @RequestHeader("ip") String ip,
                       @RequestBody ClassLoadingGauge gauge) {
        Args.checkNotBlank(appCode, "appCode(应用码)不能为空");
        return classLoadingService.insert(appCode, ip, gauge);
    }


}
